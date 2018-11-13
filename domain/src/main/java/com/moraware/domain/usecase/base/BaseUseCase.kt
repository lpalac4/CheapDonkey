package com.moraware.domain.usecase.base

import com.moraware.domain.utils.Either
import com.moraware.domain.utils.Failure
import java.util.*

abstract class BaseUseCase<T> where T: BaseResponse {

    val id: UUID = UUID.randomUUID()

    var mRequest: BaseRequest? = null
    var mResponse: BaseResponse? = null

    abstract suspend fun run(): Either<Failure, T>
    abstract fun createEmptyResponse(): BaseResponse
    abstract fun createEmptyRequest(): BaseRequest
}