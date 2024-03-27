package com.keabyte.transaction_engine.client_api.repository

import com.keabyte.transaction_engine.client_api.repository.entity.ClientEntity
import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.PageableRepository
import java.util.*

@Repository
interface ClientRepository : PageableRepository<ClientEntity, Long> {

    fun findByClientNumber(clientNumber: String): Optional<ClientEntity>
}