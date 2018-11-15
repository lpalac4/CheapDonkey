package com.moraware.domain.client.base

import com.moraware.domain.usecase.base.BaseRequest
import com.moraware.domain.usecase.base.BaseResponse
import com.moraware.domain.usecase.base.BaseUseCase
import com.moraware.domain.utils.Either
import com.moraware.domain.utils.Failure
import java.io.InputStream
import kotlin.coroutines.experimental.CoroutineContext

/**
 * The IUseCaseClient will only be responsible of executing usecases and calling back with results
 * into the proper thread.
 */
interface IUseCaseClient {

    fun observeOnThread(coroutineContext: CoroutineContext)

    fun <R: BaseRequest, T: BaseResponse, E: Failure> execute(onResult: (Either<E, T>) -> Unit, useCase: BaseUseCase<R, T, E>)
    fun addServices(inputStream: InputStream)
}