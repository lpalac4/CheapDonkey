package com.moraware.cheapdonkey.base

import android.os.Bundle
import android.support.annotation.UiThread
import android.support.v7.app.AppCompatActivity
import com.moraware.cheapdonkey.CheapDonkeyApplication
import com.moraware.cheapdonkey.dependencyinjection.controller.ControllerModule
import com.moraware.cheapdonkey.dependencyinjection.controller.IControllerComponent

abstract class BaseActivity<T: BaseViewModel>: AppCompatActivity() {

    protected lateinit var mViewModel: T

    override fun onCreate(savedInstanceState: Bundle?) {
        getViewModelComponent().inject(this)
        super.onCreate(savedInstanceState)

        setupUIAndBindViewModel()
    }

    @UiThread
    fun getViewModelComponent(): IControllerComponent {
        return (application as CheapDonkeyApplication)
                .getApplicationComponent()
                .newControllerComponent(ControllerModule(this))
    }

    abstract fun setupUIAndBindViewModel()

    override fun onStart() {
        super.onStart()
        mViewModel.loadData()
    }
}