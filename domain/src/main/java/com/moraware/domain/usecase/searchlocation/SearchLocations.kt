package com.moraware.domain.usecase.searchlocation

import com.moraware.domain.interactors.DomainResponse
import com.moraware.domain.models.Destination

class SearchLocations(destinations: List<Destination>) : DomainResponse() {
    val possibleAddresses = arrayListOf<String>()

    init {
        destinations.forEach {
            possibleAddresses.add(it.destination)
        }
    }
}