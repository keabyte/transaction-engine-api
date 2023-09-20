package com.keabyte.transactionengine.web.model

import io.micronaut.serde.annotation.Serdeable
import java.util.*

@Serdeable
class Client(
    val id: UUID,
    val firstName: String,
    val lastName: String
)