package com.moraware.cheapdonkey.dependencyinjection.application

import com.moraware.cheapdonkey.CheapDonkeyApplication
import com.moraware.cheapdonkey.logger.CheapDonkeyLogger
import dagger.Module
import dagger.Provides

/**
 * In this module we define the dependencies that are injected at a global scope
 */
@Module
open class ApplicationModule(val application: CheapDonkeyApplication = CheapDonkeyApplication()) {

    @Provides
    @ApplicationScope
    open fun provideApplication() : CheapDonkeyApplication {
        return application
    }

    @Provides
    @ApplicationScope
    open fun provideLogger() : CheapDonkeyLogger {
        return CheapDonkeyLogger("CDLogger", null)
    }
}