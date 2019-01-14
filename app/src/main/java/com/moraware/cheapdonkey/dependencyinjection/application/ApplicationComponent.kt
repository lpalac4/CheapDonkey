package com.moraware.cheapdonkey.dependencyinjection.application

import com.moraware.cheapdonkey.CheapDonkeyApplication
import com.moraware.cheapdonkey.base.BaseViewModel
import com.moraware.cheapdonkey.dependencyinjection.controller.IControllerComponent
import com.moraware.cheapdonkey.dependencyinjection.controller.ControllerModule
import dagger.Component

/**
 * This DI component will be in charged of dependency injection at a global level, together with @ApplicationDomainScope,
 * this is effectively telling Dagger to use a single instance of these objects.
 *
 * Add more modules within this same package that will benefit from sharing injected objects. For example
 * the Application object injected through this component's ApplicationModule can be used by other modules like SettingsModule.
 */
@ApplicationScope
@Component(
        modules = [
            ApplicationModule::class,
            SettingsModule::class,
            DomainDependencyModule::class
        ]
)
interface ApplicationComponent {

    /** Objects gettings their dependencies injected from this global level**/
    fun inject(application: CheapDonkeyApplication)
    fun inject(application: BaseViewModel)

    /** components that will inject to different Android Frameworks objects **/
    fun newControllerComponent(controllerModule: ControllerModule) : IControllerComponent

}