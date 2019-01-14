package com.moraware.domain.mappers

import com.moraware.data.models.TaxiService
import com.moraware.domain.models.Taxi

class TaxiMapper {
    fun transform(taxiServices: List<TaxiService>): List<Taxi> {
        var domainTaxis = arrayListOf<Taxi>()

        taxiServices.forEach {
            domainTaxis.add(Taxi(it.name, it.logoUrl))
        }

        return domainTaxis
    }

}