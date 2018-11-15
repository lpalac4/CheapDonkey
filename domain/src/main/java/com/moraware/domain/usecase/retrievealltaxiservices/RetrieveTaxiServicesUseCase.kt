package com.moraware.domain.usecase.retrievealltaxiservices

import com.moraware.domain.models.ApplicationServices
import com.moraware.domain.usecase.base.BaseUseCase
import com.moraware.domain.utils.Either

class RetrieveTaxiServicesUseCase : BaseUseCase<RetrieveTaxiServicesRequest, RetrieveTaxiServicesResponse, RetrieveTaxiServicesFailure>() {

    init {
        mRequest = createEmptyRequest()
        mResponse = createEmptyResponse()
    }

    override fun createEmptyResponse(): RetrieveTaxiServicesResponse {
        return RetrieveTaxiServicesResponse()
    }

    override fun createEmptyRequest(): RetrieveTaxiServicesRequest {
        return RetrieveTaxiServicesRequest()
    }

    override suspend fun run(): Either<RetrieveTaxiServicesFailure, RetrieveTaxiServicesResponse> {
        val applicationServices = ApplicationServices.getInstance() ?: return Either.Left(RetrieveTaxiServicesFailure())

        mResponse.taxiServices = applicationServices.taxiServices

        return Either.Right(mResponse)
    }
}