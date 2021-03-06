package com.moraware.cheapdonkey.base

import android.os.Bundle
import android.support.annotation.UiThread
import android.support.v7.app.AppCompatActivity
import com.moraware.cheapdonkey.CheapDonkeyApplication
import com.moraware.cheapdonkey.dependencyinjection.controller.ControllerModule
import com.moraware.cheapdonkey.dependencyinjection.controller.IControllerComponent
import com.moraware.cheapdonkey.logger.CheapDonkeyLogger
import javax.inject.Inject

abstract class BaseActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getControllerComponent().inject(this)
    }

    @Inject
    lateinit var mLogger: CheapDonkeyLogger

    @UiThread
    fun getControllerComponent(): IControllerComponent {
        return (application as CheapDonkeyApplication)
                .getApplicationComponent()
                .newControllerComponent(ControllerModule(this))
    }
}