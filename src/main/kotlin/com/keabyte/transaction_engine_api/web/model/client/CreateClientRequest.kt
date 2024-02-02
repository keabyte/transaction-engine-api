package com.keabyte.transaction_engine_api.web.model.client

import com.keabyte.transaction_engine_api.repository.entity.ClientEntity
import io.micronaut.serde.annotation.Serdeable
import java.time.LocalDate

@Serdeable
data class CreateClientRequest(
    val firstName: String,
    val lastName: String,
    val dateOfBirth: LocalDate
) {

    fun toEntity() = ClientEntity(
        firstName = firstName,
        lastName = lastName,
        dateOfBirth = dateOfBirth
    )
}

