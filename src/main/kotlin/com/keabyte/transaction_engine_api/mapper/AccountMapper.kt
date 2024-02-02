package com.keabyte.transaction_engine_api.mapper

import com.keabyte.transaction_engine_api.repository.ClientRepository
import com.keabyte.transaction_engine_api.repository.entity.AccountEntity
import com.keabyte.transaction_engine_api.web.model.account.CreateAccountRequest
import jakarta.inject.Singleton

@Singleton
class AccountMapper(private val clientRepository: ClientRepository) {

    fun mapCreateAccountRequestToAccountEntity(request: CreateAccountRequest) = AccountEntity(
        client = clientRepository.findByClientNumber(request.clientNumber).orElseThrow()
    )
}