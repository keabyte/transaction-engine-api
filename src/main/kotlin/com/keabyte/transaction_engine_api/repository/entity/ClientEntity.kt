package com.keabyte.transaction_engine_api.repository.entity

import com.keabyte.transaction_engine_api.web.model.client.Client
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import java.time.LocalDate

@Entity(name = "client")
data class ClientEntity(
    @Id @GeneratedValue val id: Long? = null,
    val firstName: String,
    val lastName: String,
    val dateOfBirth: LocalDate
) {
    fun toModel() = Client(
        id = id ?: 0,
        firstName = firstName,
        lastName = lastName,
        dateOfBirth = dateOfBirth
    )
}