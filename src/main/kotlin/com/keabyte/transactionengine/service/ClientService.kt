package com.keabyte.transactionengine.service

import com.keabyte.transactionengine.mapper.ClientMapper
import com.keabyte.transactionengine.repository.ClientRepository
import com.keabyte.transactionengine.web.model.Client
import com.keabyte.transactionengine.web.model.CreateClientRequest
import jakarta.inject.Singleton
import reactor.core.publisher.Mono
import java.util.*

@Singleton
class ClientService(
    private val clientRepository: ClientRepository,
    private val clientMapper: ClientMapper
) {

    fun getClientById(clientId: UUID): Mono<Client> {
        return clientRepository.findById(clientId)
            .map(clientMapper::map)
    }

    fun createClient(request: CreateClientRequest): Mono<Client> {
        return Mono.just(request)
            .map(clientMapper::map)
            .flatMap(clientRepository::save)
            .map(clientMapper::map)
    }
}