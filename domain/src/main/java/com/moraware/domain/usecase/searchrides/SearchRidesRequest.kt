package com.moraware.domain.usecase.searchrides

import com.moraware.domain.usecase.base.BaseRequest

class SearchRidesRequest: BaseRequest() {
    lateinit var startAddress: String
    var endLocationLatitude: Double? = null
    var endLocationLongitude: Double? = null
}