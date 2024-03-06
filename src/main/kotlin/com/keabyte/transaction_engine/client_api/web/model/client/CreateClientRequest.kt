package com.keabyte.transaction_engine.client_api.web.model.client

import com.keabyte.transaction_engine.client_api.repository.entity.ClientEntity
import io.micronaut.serde.annotation.Serdeable
import java.time.LocalDate

@Serdeable
data class CreateClientRequest(
    val firstName: String,
    val lastName: String,
    val dateOfBirth: LocalDate
) {

    fun toEntity() = com.keabyte.transaction_engine.client_api.repository.entity.ClientEntity(
        firstName = firstName,
        lastName = lastName,
        dateOfBirth = dateOfBirth
    )
}

