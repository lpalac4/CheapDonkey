package com.moraware.domain.usecase.retrieveAllTaxiServices

import com.moraware.domain.models.ApplicationServices
import com.moraware.domain.usecase.base.BaseRequest
import com.moraware.domain.usecase.base.BaseResponse
import com.moraware.domain.usecase.base.BaseUseCase
import com.moraware.domain.utils.Either
import com.moraware.domain.utils.Failure

class RetrieveTaxiServicesUseCase : BaseUseCase<RetrieveTaxiServicesResponse>() {

    init {
        mRequest = createEmptyRequest()
        mResponse = createEmptyResponse()
    }

    override fun createEmptyResponse(): BaseResponse {
        return RetrieveTaxiServicesResponse()
    }

    override fun createEmptyRequest(): BaseRequest {
        return RetrieveTaxiServicesRequest()
    }

    override suspend fun run(): Either<Failure, RetrieveTaxiServicesResponse> {
        if(ApplicationServices.getInstance() == null)
            return Either.Left(RetrieveTaxiServicesFailure())


        return Either.Right(RetrieveTaxiServicesResponse())
    }
}