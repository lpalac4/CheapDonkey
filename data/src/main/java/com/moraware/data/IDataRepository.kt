package com.moraware.data

import com.moraware.data.interactors.Callback
import com.moraware.data.models.SearchLocationRequest
import com.moraware.data.models.SearchLocationResponse
import com.moraware.data.models.SearchRidesRequest
import com.moraware.data.models.SearchRidesResponse
import com.moraware.data.models.ApplicationServices

interface IDataRepository {

    fun searchLocation(request: SearchLocationRequest, callback: Callback<SearchLocationResponse>)
    fun searchLocationSync(request: SearchLocationRequest) : SearchLocationResponse
    fun initializeServices(settings: ApplicationServices?, debug: Boolean)
    fun searchRides(request: SearchRidesRequest, callback: Callback<SearchRidesResponse>)
}