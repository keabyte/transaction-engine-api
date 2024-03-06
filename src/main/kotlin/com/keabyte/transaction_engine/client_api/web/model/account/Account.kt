package com.keabyte.transaction_engine.client_api.web.model.account

import io.micronaut.serde.annotation.Serdeable
import java.time.OffsetDateTime

@Serdeable
data class Account(val accountNumber: String, val clientNumber: String, val dateCreated: OffsetDateTime)