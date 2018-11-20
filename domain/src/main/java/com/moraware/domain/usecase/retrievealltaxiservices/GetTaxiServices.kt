package com.moraware.domain.usecase.retrievealltaxiservices

import com.moraware.domain.models.TaxiService
import com.moraware.domain.interactors.DomainResponse

class GetTaxiServices(val taxiServices: List<TaxiService>) : DomainResponse() {
}