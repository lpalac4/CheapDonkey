package com.moraware.data.models

import com.moraware.data.base.BaseResponse

class SearchLocationResponse(val destinations: List<Place>) : BaseResponse() {
}