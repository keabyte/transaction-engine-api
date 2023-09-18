package com.keabyte.transactionengine.repository

import com.keabyte.transactionengine.entity.ClientEntity
import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.reactive.ReactorCrudRepository

@Repository
interface ClientRepository: ReactorCrudRepository<ClientEntity, Long> {
}