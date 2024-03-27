package com.keabyte.transaction_engine.client_api.web.model.account

import com.keabyte.transaction_engine.client_api.type.AccountType
import io.micronaut.serde.annotation.Serdeable

@Serdeable
data class CreateAccountRequest(val clientNumber: String, val type: AccountType)