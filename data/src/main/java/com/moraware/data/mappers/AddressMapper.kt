package com.moraware.data.mappers

import com.moraware.data.models.SearchLocationResponse
import com.moraware.data.models.Place

class AddressMapper {
    fun transform(destinations: List<Place>?): SearchLocationResponse? {

        return if(destinations != null) SearchLocationResponse(destinations) else null
    }
}