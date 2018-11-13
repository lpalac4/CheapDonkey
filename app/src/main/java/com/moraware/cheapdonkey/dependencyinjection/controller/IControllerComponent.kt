package com.moraware.cheapdonkey.dependencyinjection.controller

import android.support.v7.app.AppCompatActivity
import dagger.Subcomponent

/** Marking this component as a Subcomponent will grant it access to parent component **/
@Subcomponent(
        modules = [
            ControllerModule::class
        ]
)
interface IControllerComponent {

    /** Objects gettings their dependencies injected **/
    fun inject(activity: AppCompatActivity)
}