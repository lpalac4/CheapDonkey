package com.moraware.cheapdonkey.dependencyinjection

import com.moraware.cheapdonkey.CheapDonkeyApplication
import com.moraware.cheapdonkey.logger.CheapDonkeyLogger
import dagger.Module
import dagger.Provides

@Module
class TestModule {

    @Provides
    fun provideApplication(): CheapDonkeyApplication {
        return MockApplication()
    }

    @Provides
    fun provideLogger(): CheapDonkeyLogger {
        return MockLogger()
    }
}