package com.moraware.domain.usecase.searchrides

import com.moraware.domain.models.Ride
import com.moraware.domain.interactors.DomainResponse

class SearchRides: DomainResponse() {
    val rides: List<Ride> = listOf()
}