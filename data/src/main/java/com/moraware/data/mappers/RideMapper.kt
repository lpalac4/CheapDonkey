package com.moraware.data.mappers

import com.moraware.data.models.Ride
import com.moraware.data.webservices.lyft.CostEstimates
import com.moraware.data.webservices.uber.Prices

class RideMapper {
    fun transform(costEstimates: List<CostEstimates>?, prices: List<Prices>?): List<Ride> {
        return arrayListOf()
    }
}