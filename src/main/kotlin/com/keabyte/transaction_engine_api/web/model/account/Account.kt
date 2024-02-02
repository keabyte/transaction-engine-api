package com.keabyte.transaction_engine_api.web.model.account

import io.micronaut.serde.annotation.Serdeable
import java.time.OffsetDateTime

@Serdeable
class Account(val clientNumber: String, val dateCreated: OffsetDateTime)