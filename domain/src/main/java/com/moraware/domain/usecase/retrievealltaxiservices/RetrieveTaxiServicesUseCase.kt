package com.moraware.domain.usecase.retrievealltaxiservices

import com.moraware.domain.mappers.TaxiMapper
import com.moraware.data.models.ApplicationServices
import com.moraware.domain.interactors.Either
import com.moraware.domain.usecase.base.BaseUseCase

class RetrieveTaxiServicesUseCase : BaseUseCase<GetTaxiServices, GetTaxiServicesFailure>() {

    override suspend fun run() {
        val applicationServices = ApplicationServices.getInstance()
        val taxiServices = applicationServices?.taxiServices

        if(taxiServices == null || taxiServices.isEmpty()) {
            mCallbackHandler?.invoke(Either.Left(GetTaxiServicesFailure()))
        }
        else {
            mCallbackHandler?.invoke(Either.Right(GetTaxiServices(TaxiMapper().transform(taxiServices))))
        }
    }
}