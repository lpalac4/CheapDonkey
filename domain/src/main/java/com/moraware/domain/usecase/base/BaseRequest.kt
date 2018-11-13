package com.moraware.domain.usecase.base

import java.util.UUID

abstract class BaseRequest {

    val id: UUID = UUID.randomUUID()
}