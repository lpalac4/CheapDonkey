package com.moraware.data

import com.moraware.data.interactors.Callback
import com.moraware.data.models.SearchLocationRequest
import com.moraware.data.models.SearchLocationResponse

class CheapDonkeyRepository: IDataRepository {

    override fun searchLocation(request: SearchLocationRequest, callback: Callback<SearchLocationResponse>) {
        callback.onSuccess(SearchLocationResponse())
    }

    override fun searchLocationSync(request: SearchLocationRequest): SearchLocationResponse {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}