package com.moraware.domain.mappers

import com.moraware.data.models.SearchLocationResponse
import com.moraware.domain.models.Destination

class DestinationMapper {
    fun transform(response: SearchLocationResponse): List<Destination> {
        var destinations = arrayListOf<Destination>()
        response.destinations?.forEach {
            destinations.add(Destination(it.formatted_address, it.geometry[0].lat, it.geometry[0].lng))
        }

        return destinations
    }

}