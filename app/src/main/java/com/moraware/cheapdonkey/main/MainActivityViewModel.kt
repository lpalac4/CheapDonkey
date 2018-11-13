package com.moraware.cheapdonkey.main

import android.os.Handler
import com.moraware.cheapdonkey.base.BaseViewModel
import com.moraware.domain.usecase.base.BaseResponse
import com.moraware.domain.usecase.retrieveAllTaxiServices.RetrieveTaxiServicesResponse
import com.moraware.domain.usecase.retrieveAllTaxiServices.RetrieveTaxiServicesUseCase
import com.moraware.domain.utils.Failure
import java.util.logging.Level

class MainActivityViewModel : BaseViewModel() {
    override fun loadData() {
        mLogger.log(Level.FINE, "Loading main activity view model data....")

        var useCase = RetrieveTaxiServicesUseCase()

        mUseCaseClient.execute({ it.either(::onRetrieveTaxiServicesFailure, ::onRetrieveTaxiServicesSuccess)}, useCase)

        // Simulate network call
        setProcessing(true)
        mLogger.log(Level.FINE, "Processing = true....")
        mLogger.log(Level.FINE, "Sleeping thread....")

        Handler().postDelayed({
            setProcessing(false)
            mLogger.log(Level.FINE, "Processing = false....")
        }, 10000)
    }

    fun onRetrieveTaxiServicesSuccess(response: RetrieveTaxiServicesResponse) {

    }

    fun onRetrieveTaxiServicesFailure(failure: Failure) {

    }
}