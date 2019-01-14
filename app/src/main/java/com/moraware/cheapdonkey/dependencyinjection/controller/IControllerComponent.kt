package com.moraware.cheapdonkey.dependencyinjection.controller

import com.moraware.cheapdonkey.base.BaseActivity
import dagger.Subcomponent

/** Marking this component as a Subcomponent will grant it access to parent component **/
@Subcomponent(
        modules = [
            ControllerModule::class
        ]
)
interface IControllerComponent {

    /** Objects gettings their dependencies injected **/
    fun inject(activity: BaseActivity)
}