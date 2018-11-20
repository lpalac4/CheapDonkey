package com.moraware.data.interactors

import com.moraware.data.base.BaseResponse
import com.moraware.data.base.WebServiceException

interface Callback<T> where T: BaseResponse {
    fun onSuccess(response: T) {}
    fun onFailure(exception: WebServiceException) {}
}