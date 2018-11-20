package com.moraware.domain.usecase.base

import com.moraware.data.IDataRepository
import com.moraware.data.base.BaseResponse
import com.moraware.data.interactors.Callback
import com.moraware.domain.client.DomainDependencyProvider
import com.moraware.domain.interactors.DomainResponse
import com.moraware.domain.interactors.Either
import com.moraware.domain.interactors.Failure
import java.util.*
import java.util.logging.Logger

abstract class BaseUseCase<T, E> where T: DomainResponse, E: Failure {

    protected var mRepository: IDataRepository = DomainDependencyProvider.getDataRepository()
    protected var mLogger: Logger? = DomainDependencyProvider.getLogger()

    protected val id: UUID = UUID.randomUUID()

//    protected lateinit var mRequest: R
//    protected lateinit var mResponse: T

    abstract suspend fun run()

    protected var mCallbackHandler: ((Either<E, T>) -> Unit)? = null
    fun setCallback(onResult: (Either<E, T>) -> Unit) {
        mCallbackHandler = onResult
    }

//    abstract fun getCallback(): Callback<R>

//    abstract fun createEmptyResponse(): T
//    abstract fun createEmptyRequest(): R
}