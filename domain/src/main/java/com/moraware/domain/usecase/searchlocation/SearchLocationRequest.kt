package com.moraware.domain.usecase.searchlocation

import com.moraware.domain.usecase.base.BaseRequest

class SearchLocationRequest: BaseRequest() {
    var address: String? = ""
}