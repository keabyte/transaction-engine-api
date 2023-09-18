package com.keabyte.transactionengine.entity

import jakarta.persistence.Entity
import jakarta.persistence.Id

@Entity(name = "client")
class ClientEntity(
    @Id val id: Long
)