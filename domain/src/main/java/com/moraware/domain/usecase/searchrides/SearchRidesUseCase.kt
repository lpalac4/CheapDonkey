package com.moraware.domain.usecase.searchrides

import com.moraware.data.base.WebServiceException
import com.moraware.data.interactors.Callback
import com.moraware.data.models.SearchRidesRequest
import com.moraware.data.models.SearchRidesResponse
import com.moraware.domain.interactors.Either
import com.moraware.domain.mappers.RideMapper
import com.moraware.domain.usecase.base.BaseUseCase

class SearchRidesUseCase(val destination: String,
                         val destinationLatitude: Double?,
                         val destinationLongitude: Double?,
                         val currentLatitude: Double?,
                         val currentLongitude: Double?) :
        BaseUseCase<SearchRides, SearchRidesFailure>() {

    private val callback = object : Callback<SearchRidesResponse> {
        override fun onFailure(exception: WebServiceException) {
            val result = Either.Left(SearchRidesFailure())
            postToMainThread(result)
        }

        override fun onSuccess(response: SearchRidesResponse) {
            val result = Either.Right(SearchRides(RideMapper().transform(response)))
            postToMainThread(result)
        }
    }

    override suspend fun run() {
        if(destinationLatitude != null && destinationLongitude != null && currentLatitude != null && currentLongitude != null) {
            val request = SearchRidesRequest(destinationLatitude, destinationLongitude, currentLatitude, currentLongitude)
            getRepository().searchRides(request, callback)
        }
    }
}