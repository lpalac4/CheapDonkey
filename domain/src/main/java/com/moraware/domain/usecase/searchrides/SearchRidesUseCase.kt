package com.moraware.domain.usecase.searchrides

import com.moraware.domain.usecase.base.BaseUseCase
import com.moraware.domain.utils.Either

class SearchRidesUseCase: BaseUseCase<SearchRidesRequest, SearchRidesResponse, SearchRidesFailure>() {
    override suspend fun run(): Either<SearchRidesFailure, SearchRidesResponse> {
        return Either.Left(SearchRidesFailure())
    }

    override fun createEmptyResponse(): SearchRidesResponse {
        return SearchRidesResponse()
    }

    override fun createEmptyRequest(): SearchRidesRequest {
        return SearchRidesRequest()
    }
}