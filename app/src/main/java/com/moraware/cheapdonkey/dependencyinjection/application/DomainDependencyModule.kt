package com.moraware.cheapdonkey.dependencyinjection.application

import com.moraware.cheapdonkey.CheapDonkeyApplication
import com.moraware.domain.client.CheapDonkeyUseCaseClient
import com.moraware.domain.client.base.IUseCaseClient
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.experimental.android.UI

/**
 * This module will hold domain project dependencies injected throughout the app
 */
@Module
class DomainDependencyModule(val application: CheapDonkeyApplication) {

    @Provides
    @ApplicationScope
    fun providesUseCaseClient(application: CheapDonkeyApplication) : IUseCaseClient {
        val client = CheapDonkeyUseCaseClient()
        client.observeOnThread(UI)
        client.addServices(application.resources.assets.open("applicationsettings.json"))
        return client
    }
}