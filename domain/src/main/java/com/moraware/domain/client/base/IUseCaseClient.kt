package com.moraware.domain.client.base

import com.moraware.data.IDataRepository
import com.moraware.domain.interactors.DomainResponse
import com.moraware.domain.interactors.Either
import com.moraware.domain.interactors.Failure
import com.moraware.domain.usecase.base.BaseUseCase
import java.io.InputStream
import kotlin.coroutines.experimental.CoroutineContext

/**
 * The IUseCaseClient will only be responsible of executing usecases and calling back with results
 * into the proper thread.
 */
interface IUseCaseClient {

    fun observeOnThread(coroutineContext: CoroutineContext)

    fun <T: DomainResponse, E: Failure> execute(onResult: (Either<E, T>) -> Unit, useCase: BaseUseCase<T, E>)
    fun addServices(inputStream: InputStream)
    fun setRepository(repository: IDataRepository)
}