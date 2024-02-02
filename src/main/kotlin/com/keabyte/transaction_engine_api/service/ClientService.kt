package com.keabyte.transaction_engine_api.service

import com.keabyte.transaction_engine_api.exception.BusinessException
import com.keabyte.transaction_engine_api.repository.ClientRepository
import com.keabyte.transaction_engine_api.repository.entity.ClientEntity
import com.keabyte.transaction_engine_api.web.model.client.Client
import com.keabyte.transaction_engine_api.web.model.client.CreateClientRequest
import jakarta.inject.Singleton

@Singleton
class ClientService(
    private val clientRepository: ClientRepository,
) {

    fun getClientByExternalId(clientNumber: String): Client {
        return clientRepository.findByClientNumber(clientNumber).map(ClientEntity::toModel)
            .orElseThrow { BusinessException("No client exists with client number $clientNumber") }
    }

    fun createClient(request: CreateClientRequest): Client {
        return clientRepository.save(request.toEntity()).toModel()
    }
}