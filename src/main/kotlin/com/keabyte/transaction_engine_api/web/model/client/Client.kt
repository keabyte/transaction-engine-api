package com.keabyte.transaction_engine_api.web.model.client

import io.micronaut.serde.annotation.Serdeable
import java.time.LocalDate

@Serdeable
data class Client(
    val clientNumber: String,
    val firstName: String,
    val lastName: String,
    val dateOfBirth: LocalDate
)