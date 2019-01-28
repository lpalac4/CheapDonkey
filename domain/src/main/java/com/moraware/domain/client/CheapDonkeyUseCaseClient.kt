package com.moraware.domain.client

import com.moraware.data.CheapDonkeyRepository
import com.moraware.data.IDataRepository
import com.moraware.data.models.ApplicationServices
import com.moraware.domain.client.base.IUseCaseClient
import com.moraware.domain.interactors.DomainResponse
import com.moraware.domain.interactors.Either
import com.moraware.domain.interactors.Failure
import com.moraware.domain.usecase.base.BaseUseCase
import kotlinx.coroutines.experimental.async
import java.io.InputStream
import java.util.logging.Logger
import kotlin.coroutines.experimental.CoroutineContext

open class CheapDonkeyUseCaseClient : IUseCaseClient {

    private lateinit var mCoroutineContext: CoroutineContext

    init {
        DomainDependencyProvider.setDataRepository(CheapDonkeyRepository())
        DomainDependencyProvider.setLogger(Logger.getLogger("CheapDonkeyUseCaseClient"))
    }

    override fun observeOnThread(coroutineContext: CoroutineContext) {
        mCoroutineContext = coroutineContext
    }

    override fun setRepository(repository: IDataRepository){
        DomainDependencyProvider.setDataRepository(repository)
    }

    @Synchronized override fun <T : DomainResponse, E: Failure> execute(onResult: (Either<E, T>) -> Unit, useCase: BaseUseCase<T, E>) {
        mCoroutineContext.let {
            async(mCoroutineContext) {
                useCase.setCallback(onResult)
                useCase.setMainLooper(mCoroutineContext)
                useCase.run()
            }
        }
    }

    override fun addServices(inputStream: InputStream, debug: Boolean) {
        ApplicationServices.initialize(inputStream)
        DomainDependencyProvider.getDataRepository().initializeServices(ApplicationServices.getInstance(), debug)
    }
}