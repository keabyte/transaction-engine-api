package com.keabyte.transaction_engine.client_api.repository

import com.keabyte.transaction_engine.client_api.repository.entity.ClientEntity
import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository
import java.util.*

@Repository
interface ClientRepository : JpaRepository<ClientEntity, Long> {

    fun findByClientNumber(clientNumber: String): Optional<ClientEntity>
}