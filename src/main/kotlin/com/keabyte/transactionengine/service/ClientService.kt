package com.keabyte.transactionengine.service

import com.keabyte.transactionengine.entity.ClientEntity
import com.keabyte.transactionengine.mapper.ClientMapper
import com.keabyte.transactionengine.repository.ClientRepository
import com.keabyte.transactionengine.web.model.Client
import com.keabyte.transactionengine.web.model.CreateClientRequest
import jakarta.inject.Singleton
import reactor.core.publisher.Mono

@Singleton
class ClientService(
    private val clientRepository: ClientRepository,
    private val clientMapper: ClientMapper
) {

    fun getClientById(clientId: Long): Mono<Client> {
        return clientRepository.findById(clientId)
            .map(clientMapper::map)
    }

    fun createClient(request: CreateClientRequest): Mono<Client> {
        return clientRepository.save(ClientEntity(1L))
            .map(clientMapper::map)
    }
}