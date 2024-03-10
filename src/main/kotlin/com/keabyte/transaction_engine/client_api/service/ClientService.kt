package com.keabyte.transaction_engine.client_api.service

import com.keabyte.transaction_engine.client_api.exception.BusinessException
import com.keabyte.transaction_engine.client_api.repository.ClientRepository
import com.keabyte.transaction_engine.client_api.web.model.client.Client
import com.keabyte.transaction_engine.client_api.web.model.client.CreateClientRequest
import jakarta.inject.Singleton
import jakarta.validation.constraints.NotBlank

@Singleton
open class ClientService(
    private val clientRepository: ClientRepository,
) {

    open fun getClientById(@NotBlank clientNumber: String): Client {
        return clientRepository.findByClientNumber(clientNumber).map { it.toModel() }
            .orElseThrow { BusinessException("No client exists with client number $clientNumber") }
    }

    fun createClient(request: CreateClientRequest): Client {
        return clientRepository.saveAndFlush(request.toEntity()).toModel()
    }
}