package com.moraware.cheapdonkey.dependencyinjection.application

import android.os.Handler
import com.moraware.cheapdonkey.BuildConfig
import com.moraware.cheapdonkey.CheapDonkeyApplication
import com.moraware.domain.client.CheapDonkeyUseCaseClient
import com.moraware.domain.client.base.IUseCaseClient
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.experimental.android.HandlerContext

/**
 * This module will hold domain project dependencies injected throughout the app
 */
@Module
class DomainDependencyModule(val application: CheapDonkeyApplication) {

    @Provides
    @ApplicationScope
    fun providesUseCaseClient(application: CheapDonkeyApplication) : IUseCaseClient {
        val client = CheapDonkeyUseCaseClient()
        client.observeOnThread(HandlerContext(Handler()))
        client.addServices(application.resources.assets.open("applicationsettings.json"), BuildConfig.DEBUG)
        return client
    }
}