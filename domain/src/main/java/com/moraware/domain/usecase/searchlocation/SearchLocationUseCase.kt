package com.moraware.domain.usecase.searchlocation

import com.moraware.data.base.WebServiceException
import com.moraware.data.interactors.Callback
import com.moraware.data.models.SearchLocationRequest
import com.moraware.data.models.SearchLocationResponse
import com.moraware.domain.interactors.Either
import com.moraware.domain.usecase.base.BaseUseCase

class SearchLocationUseCase(val address: String?) : BaseUseCase<SearchLocations, SearchLocationFailure>() {

    private val callback = object : Callback<SearchLocationResponse> {
        override fun onFailure(exception: WebServiceException) {
            mCallbackHandler?.invoke(Either.Left(SearchLocationFailure()))
        }

        override fun onSuccess(response: SearchLocationResponse) {
            super.onSuccess(response)
            mCallbackHandler?.invoke(Either.Right(SearchLocations()))
        }
    }

    override suspend fun run() {
        var request = SearchLocationRequest()
        request.address = address
        mRepository.searchLocation(request,  callback)
    }
}