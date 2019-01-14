package com.moraware.domain.usecase.searchrides

import com.moraware.data.base.WebServiceException
import com.moraware.data.interactors.Callback
import com.moraware.domain.mappers.RideMapper
import com.moraware.data.models.SearchRidesRequest
import com.moraware.data.models.SearchRidesResponse
import com.moraware.domain.interactors.Either
import com.moraware.domain.usecase.base.BaseUseCase

class SearchRidesUseCase(val destination: String,
                         val destinationLatitude: Double?,
                         val destinationLongitude: Double?,
                         val latitude: Double?,
                         val longitude: Double?) :
        BaseUseCase<SearchRides, SearchRidesFailure>() {

    private val callback = object : Callback<SearchRidesResponse> {
        override fun onFailure(exception: WebServiceException) {
            mCallbackHandler?.invoke(Either.Left(SearchRidesFailure()))
        }

        override fun onSuccess(response: SearchRidesResponse) {
            super.onSuccess(response)
            mCallbackHandler?.invoke(Either.Right(SearchRides(RideMapper().transform(response))))
        }
    }

    override suspend fun run() {
        if(destinationLatitude != null && destinationLongitude != null && latitude != null && longitude != null) {
            val request = SearchRidesRequest(destinationLatitude, destinationLongitude, latitude, longitude)
            getRepository().searchRides(request, callback)
        }
    }
}