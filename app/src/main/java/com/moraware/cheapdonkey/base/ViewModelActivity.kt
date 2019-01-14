package com.moraware.cheapdonkey.base

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.annotation.UiThread
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.moraware.cheapdonkey.CheapDonkeyApplication
import com.moraware.cheapdonkey.dependencyinjection.controller.ControllerModule
import com.moraware.cheapdonkey.dependencyinjection.controller.IControllerComponent
import com.moraware.cheapdonkey.logger.CheapDonkeyLogger
import javax.inject.Inject

abstract class ViewModelActivity<T: BaseViewModel>: BaseActivity() {

    protected lateinit var mViewModel: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupUIAndBindViewModel()
    }

    abstract fun setupUIAndBindViewModel()

    override fun onStart() {
        super.onStart()
        mViewModel.loadData()

        mViewModel.getErrorMessage().observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })
    }
}