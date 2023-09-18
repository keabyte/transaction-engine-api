package com.keabyte.transactionengine.web.model

import io.micronaut.core.annotation.Introspected
import io.micronaut.serde.annotation.Serdeable

@Introspected
@Serdeable
class Client(
    val id: Long
)