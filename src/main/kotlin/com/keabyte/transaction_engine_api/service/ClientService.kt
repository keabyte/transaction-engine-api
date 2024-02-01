package com.keabyte.transaction_engine_api.service

import com.keabyte.transaction_engine_api.repository.ClientRepository
import com.keabyte.transaction_engine_api.repository.entity.ClientEntity
import com.keabyte.transaction_engine_api.web.model.client.Client
import com.keabyte.transaction_engine_api.web.model.client.CreateClientRequest
import jakarta.inject.Singleton

@Singleton
class ClientService(
    private val clientRepository: ClientRepository,
) {

    fun getClientByExternalId(clientId: String): Client {
        return clientRepository.findByClientNumber(clientId).map(ClientEntity::toModel).orElseThrow()
    }

    fun createClient(request: CreateClientRequest): Client {
        return clientRepository.save(request.toEntity()).toModel()
    }
}