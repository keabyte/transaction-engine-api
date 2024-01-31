package com.keabyte.transactionengine.web.model.client

import io.micronaut.serde.annotation.Serdeable

@Serdeable
class CreateClientRequest(
    val firstName: String,
    val lastName: String?
)