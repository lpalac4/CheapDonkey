package com.moraware.domain.usecase.retrievealltaxiservices

import com.moraware.domain.interactors.DomainResponse
import com.moraware.domain.models.Taxi

class GetTaxiServices(val taxiServices: List<Taxi>) : DomainResponse() {
}