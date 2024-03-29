package com.keabyte.transaction_engine.client_api.repository.entity

import com.keabyte.transaction_engine.client_api.web.model.client.Client
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.validation.constraints.NotBlank
import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDate
import java.time.OffsetDateTime
import java.util.*

@Entity(name = "client")
data class ClientEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long? = null,
    val clientNumber: String = UUID.randomUUID().toString(),
    @NotBlank val firstName: String,
    @NotBlank val lastName: String,
    val dateOfBirth: LocalDate,
    @CreationTimestamp
    val createdDate: OffsetDateTime? = null,
) {
    fun toModel() = Client(
        clientNumber = clientNumber,
        firstName = firstName,
        lastName = lastName,
        dateOfBirth = dateOfBirth
    )
}