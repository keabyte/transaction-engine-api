package com.keabyte.transaction_engine_api.web.model.client

import io.micronaut.serde.annotation.Serdeable
import java.util.*

@Serdeable
class Client(
    val id: Long,
    val firstName: String,
    val lastName: String
)