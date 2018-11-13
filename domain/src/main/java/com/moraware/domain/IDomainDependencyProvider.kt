package com.moraware.domain

import com.moraware.domain.client.CheapDonkeyUseCaseClient
import com.moraware.domain.client.base.IUseCaseClient

interface IDomainDependencyProvider {
    fun getUseCaseClient(): IUseCaseClient {
        return CheapDonkeyUseCaseClient()
    }
}