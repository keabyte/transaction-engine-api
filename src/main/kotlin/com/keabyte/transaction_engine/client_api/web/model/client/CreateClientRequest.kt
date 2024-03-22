package com.keabyte.transaction_engine.client_api.web.model.client

import com.fasterxml.jackson.annotation.JsonFormat
import com.keabyte.transaction_engine.client_api.repository.entity.ClientEntity
import io.micronaut.serde.annotation.Serdeable
import java.time.LocalDate

@Serdeable
data class CreateClientRequest(
    val firstName: String,
    val lastName: String,
    @JsonFormat(pattern = "yyyy-MM-dd")
    val dateOfBirth: LocalDate,
) {

    fun toEntity() = ClientEntity(
        firstName = firstName,
        lastName = lastName,
        dateOfBirth = dateOfBirth
    )
}

