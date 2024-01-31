package com.keabyte.transaction_engine_api.web.model.client

import com.keabyte.transaction_engine_api.repository.entity.ClientEntity
import io.micronaut.serde.annotation.Serdeable
import java.util.*

@Serdeable
class CreateClientRequest(
    val firstName: String,
    val lastName: String
) {

    fun toEntity() = ClientEntity(
        id = -1,
        firstName = firstName,
        lastName = lastName,
    )
}

