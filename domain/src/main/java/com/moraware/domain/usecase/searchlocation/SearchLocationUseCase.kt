package com.moraware.domain.usecase.searchlocation

import com.moraware.domain.usecase.base.BaseUseCase
import com.moraware.domain.utils.Either

class SearchLocationUseCase: BaseUseCase<SearchLocationRequest, SearchLocationResponse, SearchLocationFailure>() {
    override suspend fun run(): Either<SearchLocationFailure, SearchLocationResponse> {
        return Either.Left(SearchLocationFailure())
    }

    override fun createEmptyResponse(): SearchLocationResponse {
        return SearchLocationResponse()
    }

    override fun createEmptyRequest(): SearchLocationRequest {
        return SearchLocationRequest()
    }
}