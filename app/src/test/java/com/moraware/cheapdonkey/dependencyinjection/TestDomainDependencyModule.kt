package com.moraware.cheapdonkey.dependencyinjection

import com.moraware.domain.client.base.IUseCaseClient
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.experimental.android.UI

@Module
class TestDomainDependencyModule {

    @Provides
    fun providesUseCaseClient() : IUseCaseClient {
        val client = MockUseCaseClient()
        client.observeOnThread(UI)
        return client
    }
}