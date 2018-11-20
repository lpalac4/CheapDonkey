package com.moraware.data

import com.moraware.data.interactors.Callback
import com.moraware.data.models.SearchLocationRequest
import com.moraware.data.models.SearchLocationResponse

interface IDataRepository {

    fun searchLocation(request: SearchLocationRequest, callback: Callback<SearchLocationResponse>)
    fun searchLocationSync(request: SearchLocationRequest) : SearchLocationResponse
}