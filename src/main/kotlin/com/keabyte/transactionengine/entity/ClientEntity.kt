package com.keabyte.transactionengine.entity

import io.micronaut.serde.annotation.Serdeable
import jakarta.persistence.Entity
import jakarta.persistence.Id

@Entity(name = "client")
@Serdeable
class ClientEntity(
    @Id val id: Long
)