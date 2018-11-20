package com.moraware.domain.usecase.retrievealltaxiservices

import com.moraware.domain.models.ApplicationServices
import com.moraware.domain.usecase.base.BaseUseCase
import com.moraware.domain.interactors.Either

class RetrieveTaxiServicesUseCase : BaseUseCase<GetTaxiServices, GetTaxiServicesFailure>() {

    override suspend fun run(): Either<GetTaxiServicesFailure, GetTaxiServices> {
        val applicationServices = ApplicationServices.getInstance() ?: return Either.Left(GetTaxiServicesFailure())

        val taxiServices = applicationServices.taxiServices

        return taxiServices.isEmpty().let {
            if(it) Either.Left(GetTaxiServicesFailure()) else Either.Right(GetTaxiServices(taxiServices))
        }
    }
}