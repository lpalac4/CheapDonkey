package com.moraware.domain.mappers

import com.moraware.data.models.SearchRidesResponse
import com.moraware.domain.models.Ride

class RideMapper {
    fun transform(response: SearchRidesResponse): List<Ride> {
        var domainRides : ArrayList<Ride> = arrayListOf()
        response.rides.sortedBy { it.fare }.forEach {
            domainRides.add(Ride(it.name, it.fare, it.company, it.currency))
        }

        return domainRides
    }
}