package com.keabyte.transactionengine.entity

import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.util.*

@Entity(name = "client")
class ClientEntity(
    @Id val id: UUID,
    val firstName: String,
    val lastName: String?
)