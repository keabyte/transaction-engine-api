package com.keabyte.transaction_engine.client_api.web.model.account

import io.micronaut.serde.annotation.Serdeable

@Serdeable
data class CreateAccountRequest(val clientNumber: String)