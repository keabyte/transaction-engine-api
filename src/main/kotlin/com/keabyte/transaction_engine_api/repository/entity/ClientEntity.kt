package com.keabyte.transaction_engine_api.repository.entity

import com.keabyte.transaction_engine_api.web.model.client.Client
import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.util.*

@Entity(name = "client")
class ClientEntity(
    @Id val id: Long,
    val firstName: String,
    val lastName: String,
) {
    fun toModel() = Client(
        id = id,
        firstName = firstName,
        lastName = lastName
    )
}