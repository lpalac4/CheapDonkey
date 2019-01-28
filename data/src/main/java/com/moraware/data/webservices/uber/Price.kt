package com.moraware.data.webservices.uber

data class Price(val product_id: String,
                 val currency_code: String?,
                 val display_name: String,
                 val localized_display_name: String,
                 val estimate: String,
                 val minimum: Int,
                 val low_estimate: Int?,
                 val high_estimate: Int?,
                 val surge_multiplier: Float,
                 val duration: Int,
                 val distance: Float)