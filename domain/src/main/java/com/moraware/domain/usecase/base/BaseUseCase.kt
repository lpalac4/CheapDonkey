package com.moraware.domain.usecase.base

import com.moraware.data.IDataRepository
import com.moraware.domain.client.DomainDependencyProvider
import com.moraware.domain.interactors.DomainResponse
import com.moraware.domain.interactors.Either
import com.moraware.domain.interactors.Failure
import kotlinx.coroutines.experimental.launch
import java.util.*
import java.util.logging.Logger
import kotlin.coroutines.experimental.CoroutineContext

abstract class BaseUseCase<T, E> where T: DomainResponse, E: Failure {

    private var mRepository: IDataRepository = DomainDependencyProvider.getDataRepository()
    @Synchronized fun getRepository(): IDataRepository { return mRepository }
    protected var mLogger: Logger? = DomainDependencyProvider.getLogger()

    protected val id: UUID = UUID.randomUUID()

    abstract suspend fun run()

    private var mCallback: ((Either<E, T>) -> Unit)? = null
    fun setCallback(onResult: (Either<E, T>) -> Unit) {
        mCallback = onResult
    }

    private var mContext: CoroutineContext? = null
    @Synchronized
    fun getMainLooper(): CoroutineContext? {
        return mContext
    }
    fun setMainLooper(context: CoroutineContext) {
        mContext = context
    }

    fun postToMainThread(result: Either.Left<E>) {
        getMainLooper()?.let {
            launch(it) { mCallback?.invoke(result) }
        }
    }

    fun postToMainThread(result: Either.Right<T>) {
        getMainLooper()?.let {
            launch(it) { mCallback?.invoke(result)}
        }
    }
}