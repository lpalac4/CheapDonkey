package com.moraware.cheapdonkey

import android.app.Application
import android.support.annotation.UiThread
import com.moraware.cheapdonkey.dependencyinjection.application.ApplicationModule
import com.moraware.cheapdonkey.dependencyinjection.application.DaggerIApplicationComponent
import com.moraware.cheapdonkey.dependencyinjection.application.DomainDependencyModule
import com.moraware.cheapdonkey.dependencyinjection.application.IApplicationComponent
import com.moraware.cheapdonkey.logger.CheapDonkeyLogger
import dagger.Module
import javax.inject.Inject

@Module
class CheapDonkeyApplication : Application() {

    lateinit var mApplicationComponent: IApplicationComponent

    @Inject
    lateinit var mLogger: CheapDonkeyLogger

    override fun onCreate() {
        // instantiate component that will be used to inject on global scope here
        mApplicationComponent = DaggerIApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .domainDependencyModule(DomainDependencyModule(this))
                .build()

        mApplicationComponent.inject(this)

        super.onCreate()

        sInstance = this
    }

    @UiThread
    fun getApplicationComponent(): IApplicationComponent {
        return mApplicationComponent
    }

    companion object {
        lateinit var sInstance: CheapDonkeyApplication
        fun getInstance(): CheapDonkeyApplication {
            return sInstance
        }
    }
}
