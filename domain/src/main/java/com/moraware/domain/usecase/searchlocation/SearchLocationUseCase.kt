package com.moraware.domain.usecase.searchlocation

import com.moraware.data.base.WebServiceException
import com.moraware.data.interactors.Callback
import com.moraware.data.models.SearchLocationRequest
import com.moraware.data.models.SearchLocationResponse
import com.moraware.domain.interactors.Either
import com.moraware.domain.mappers.DestinationMapper
import com.moraware.domain.usecase.base.BaseUseCase

class SearchLocationUseCase(val searchQuery: String) : BaseUseCase<SearchLocations, SearchLocationFailure>() {

    private val callback = object : Callback<SearchLocationResponse> {
        override fun onFailure(exception: WebServiceException) {
            mCallbackHandler?.invoke(Either.Left(SearchLocationFailure()))
        }

        override fun onSuccess(response: SearchLocationResponse) {
            super.onSuccess(response)
            val destinations = DestinationMapper().transform(response)
            mCallbackHandler?.invoke(Either.Right(SearchLocations(destinations)))
        }
    }

    override suspend fun run() {
        var request = SearchLocationRequest()
        request.address = searchQuery
        getRepository().searchLocation(request, callback)
    }
}