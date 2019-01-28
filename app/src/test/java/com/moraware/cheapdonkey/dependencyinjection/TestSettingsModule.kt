package com.moraware.cheapdonkey.dependencyinjection

import com.moraware.cheapdonkey.dependencyinjection.application.ApplicationScope
import com.moraware.cheapdonkey.settings.SettingsManager
import dagger.Module
import dagger.Provides

@Module
class TestSettingsModule {

    @Provides
    @ApplicationScope
    fun provideSettingsManager() : SettingsManager {
        return MockSettingsManager()
    }
}