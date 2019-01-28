package com.moraware.data.mappers

import com.moraware.data.models.Ride
import com.moraware.data.models.TaxiService
import com.moraware.data.webservices.lyft.CostEstimate
import com.moraware.data.webservices.uber.Price

class RideMapper {
    fun transform(costEstimates: Array<CostEstimate>?, prices: Array<Price>?): List<Ride> {
        var rides: ArrayList<Ride> = arrayListOf()
        costEstimates?.forEach {
            if(it.estimated_cost_cents_min != null && it.currency != null) {
                rides.add(Ride(TaxiService.LyftService, it.display_name, it.estimated_cost_cents_min / 60, it.currency))
            }
        }

        prices?.forEach {
            if(it.low_estimate != null && it.currency_code != null)
                rides.add(Ride(TaxiService.UberService, it.display_name, it.low_estimate, it.currency_code))
        }

        return rides
    }
}