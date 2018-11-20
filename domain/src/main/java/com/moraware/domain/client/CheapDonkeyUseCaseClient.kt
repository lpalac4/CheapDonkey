package com.moraware.domain.client

import com.moraware.data.CheapDonkeyRepository
import com.moraware.data.IDataRepository
import com.moraware.domain.client.base.IUseCaseClient
import com.moraware.domain.interactors.DomainResponse
import com.moraware.domain.interactors.Either
import com.moraware.domain.interactors.Failure
import com.moraware.domain.models.ApplicationServices
import com.moraware.domain.usecase.base.BaseUseCase
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch
import java.io.InputStream
import java.util.logging.Logger
import kotlin.coroutines.experimental.CoroutineContext

class CheapDonkeyUseCaseClient: IUseCaseClient {

    private lateinit var mCoroutineContext: CoroutineContext

    constructor(){
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
            async(CommonPool) {
                useCase.setCallback(onResult)
                useCase.run()
            }
//            launch(it) { onResult.invoke(job.await()) }
//            val job = async(CommonPool) { useCase.run() }
//            launch(it) { onResult.invoke(job.await()) }
        }
    }

    override fun addServices(inputStream: InputStream) {
        ApplicationServices.initialize(inputStream)
    }
}