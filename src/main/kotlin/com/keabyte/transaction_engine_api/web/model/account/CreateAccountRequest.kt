package com.keabyte.transaction_engine_api.web.model.account

import io.micronaut.serde.annotation.Serdeable

@Serdeable
class CreateAccountRequest(val clientNumber: String)