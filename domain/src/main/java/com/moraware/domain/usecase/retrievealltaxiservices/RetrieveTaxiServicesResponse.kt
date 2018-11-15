package com.moraware.domain.usecase.retrievealltaxiservices

import com.moraware.domain.models.TaxiService
import com.moraware.domain.usecase.base.BaseResponse

class RetrieveTaxiServicesResponse: BaseResponse() {

    var taxiServices: List<TaxiService> = listOf()
}