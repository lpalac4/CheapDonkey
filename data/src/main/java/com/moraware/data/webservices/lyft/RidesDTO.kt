package com.moraware.data.webservices.lyft

import com.google.gson.annotations.SerializedName

class RidesDTO(@SerializedName("cost_estimates") val rides: Array<CostEstimate>) {
}