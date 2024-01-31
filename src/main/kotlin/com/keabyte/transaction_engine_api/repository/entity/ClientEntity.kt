package com.keabyte.transaction_engine_api.repository.entity

import com.keabyte.transaction_engine_api.web.model.client.Client
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id

@Entity(name = "client")
class ClientEntity(
    @Id @GeneratedValue val id: Long? = null,
    val firstName: String,
    val lastName: String,
) {
    fun toModel() = Client(
        id = id ?: 0,
        firstName = firstName,
        lastName = lastName
    )
}