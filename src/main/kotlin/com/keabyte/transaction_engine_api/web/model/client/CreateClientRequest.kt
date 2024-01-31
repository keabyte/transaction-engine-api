package com.keabyte.transaction_engine_api.web.model.client

import com.keabyte.transaction_engine_api.repository.entity.ClientEntity
import io.micronaut.serde.annotation.Serdeable

@Serdeable
class CreateClientRequest(
    val firstName: String,
    val lastName: String
) {

    fun toEntity() = ClientEntity(
        firstName = firstName,
        lastName = lastName,
    )
}

