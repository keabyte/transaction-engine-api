package com.keabyte.transaction_engine_api.repository

import com.keabyte.transaction_engine_api.repository.entity.ClientEntity
import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.CrudRepository
import java.util.*

@Repository
interface ClientRepository : CrudRepository<ClientEntity, Long> {

    fun findByClientNumber(clientNumber: String): Optional<ClientEntity>
}