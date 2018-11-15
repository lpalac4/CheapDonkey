package com.moraware.domain.usecase.base

import com.moraware.domain.utils.Either
import com.moraware.domain.utils.Failure
import java.util.*

abstract class BaseUseCase<R, T, E> where R: BaseRequest, T: BaseResponse, E: Failure {

    val id: UUID = UUID.randomUUID()

    lateinit var mRequest: R
    lateinit var mResponse: T

    abstract suspend fun run(): Either<E, T>
    abstract fun createEmptyResponse(): T
    abstract fun createEmptyRequest(): R
}