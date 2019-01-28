package com.moraware.domain.models

import java.text.NumberFormat
import java.util.*

class Ride(val tierName: String,
           val cost: Int,
           val taxiService: String,
           currency: String) {

    var formattedPrice: String

    init {
        var formatter = NumberFormat.getCurrencyInstance()
        formatter.currency = Currency.getInstance(currency)
        formattedPrice = formatter.format(cost)
    }

}