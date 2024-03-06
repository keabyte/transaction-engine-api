package com.keabyte.transaction_engine.client_api.repository

import com.keabyte.transaction_engine.client_api.repository.entity.ClientEntity
import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.CrudRepository
import java.util.*

@Repository
interface ClientRepository :
    CrudRepository<com.keabyte.transaction_engine.client_api.repository.entity.ClientEntity, Long> {

    fun findByClientNumber(clientNumber: String): Optional<com.keabyte.transaction_engine.client_api.repository.entity.ClientEntity>
}