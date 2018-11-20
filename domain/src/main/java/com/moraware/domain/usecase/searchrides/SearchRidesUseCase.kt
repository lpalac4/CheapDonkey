package com.moraware.domain.usecase.searchrides

import com.moraware.domain.usecase.base.BaseUseCase
import com.moraware.domain.interactors.Either

class SearchRidesUseCase(val destination: String, val latitude: Double?, val longitude: Double?) :
        BaseUseCase<SearchRides, SearchRidesFailure>() {

    override suspend fun run(): Either<SearchRidesFailure, SearchRides> {

//        mRepository.searchForRides(mRequest.startAddress, mRequest.endLocationLatitude, mRequest.endLocationLongitude)

        return Either.Left(SearchRidesFailure())
    }
}