package com.moraware.domain.client

import com.moraware.domain.client.base.IUseCaseClient
import com.moraware.domain.models.ApplicationServices
import com.moraware.domain.usecase.base.BaseResponse
import com.moraware.domain.usecase.base.BaseUseCase
import com.moraware.domain.utils.Either
import com.moraware.domain.utils.Failure
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch
import java.io.IOException
import java.io.InputStream
import kotlin.coroutines.experimental.CoroutineContext

class CheapDonkeyUseCaseClient: IUseCaseClient {

    private lateinit var mCoroutineContext: CoroutineContext

    override fun observeOnThread(coroutineContext: CoroutineContext) {
        mCoroutineContext = coroutineContext
    }

    override fun <T : BaseResponse> execute(onResult: (Either<Failure, T>) -> Unit, useCase: BaseUseCase<T>) {
        mCoroutineContext.let {
            val job = async(CommonPool) { useCase.run() }
            launch(it) { onResult.invoke(job.await()) }
        }
    }

    override fun addServices(inputStream: InputStream) {
        ApplicationServices.initialize(inputStream)
    }
}