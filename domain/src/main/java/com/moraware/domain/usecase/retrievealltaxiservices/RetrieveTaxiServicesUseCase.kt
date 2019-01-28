package com.moraware.domain.usecase.retrievealltaxiservices

import com.moraware.data.models.ApplicationServices
import com.moraware.domain.interactors.Either
import com.moraware.domain.mappers.TaxiMapper
import com.moraware.domain.usecase.base.BaseUseCase

class RetrieveTaxiServicesUseCase : BaseUseCase<GetTaxiServices, GetTaxiServicesFailure>() {

    override suspend fun run() {
        val applicationServices = ApplicationServices.getInstance()
        val taxiServices = applicationServices?.taxiServices

        if(taxiServices == null || taxiServices.isEmpty()) {
            val result = Either.Left(GetTaxiServicesFailure())
            postToMainThread(result)
        }
        else {
            val result = Either.Right(GetTaxiServices(TaxiMapper().transform(taxiServices)))
            postToMainThread(result)
        }
    }
}