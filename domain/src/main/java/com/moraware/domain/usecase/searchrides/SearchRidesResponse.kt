package com.moraware.domain.usecase.searchrides

import com.moraware.domain.models.Ride
import com.moraware.domain.usecase.base.BaseResponse

class SearchRidesResponse: BaseResponse() {
    val rides: List<Ride> = listOf()
}