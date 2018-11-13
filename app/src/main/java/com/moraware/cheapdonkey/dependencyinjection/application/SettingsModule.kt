package com.moraware.cheapdonkey.dependencyinjection.application

import android.app.Application
import com.moraware.cheapdonkey.settings.SettingsManager
import dagger.Module
import dagger.Provides

/**
 * This module defines dependencies injected for anyone needing an application settings object
 */
@Module
class SettingsModule {

    @Provides
    @ApplicationScope
    fun provideSettingsManager(application: Application) : SettingsManager {
        return SettingsManager()
    }
}