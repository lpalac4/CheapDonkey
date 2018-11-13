package com.moraware.domain.usecase.base

import java.util.UUID

abstract class BaseResponse {

    val id: UUID = UUID.randomUUID()
}