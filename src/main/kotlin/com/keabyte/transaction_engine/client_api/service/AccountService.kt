package com.keabyte.transaction_engine.client_api.service

import com.keabyte.transaction_engine.client_api.exception.BusinessException
import com.keabyte.transaction_engine.client_api.mapper.AccountMapper
import com.keabyte.transaction_engine.client_api.repository.AccountRepository
import com.keabyte.transaction_engine.client_api.repository.entity.AccountEntity
import com.keabyte.transaction_engine.client_api.web.model.account.CreateAccountRequest
import jakarta.inject.Singleton
import jakarta.validation.constraints.NotBlank

@Singleton
open class AccountService(
    private val accountRepository: com.keabyte.transaction_engine.client_api.repository.AccountRepository,
    private val accountMapper: com.keabyte.transaction_engine.client_api.mapper.AccountMapper,
) {

    fun createAccount(request: CreateAccountRequest): com.keabyte.transaction_engine.client_api.repository.entity.AccountEntity {
        val accountEntity = accountMapper.mapCreateAccountRequestToAccountEntity(request)
        return accountRepository.save(accountEntity)
    }

    open fun getAccountById(@NotBlank accountNumber: String): com.keabyte.transaction_engine.client_api.repository.entity.AccountEntity {
        return accountRepository.findByAccountNumber(accountNumber)
            .orElseThrow { com.keabyte.transaction_engine.client_api.exception.BusinessException("No account exists with account number $accountNumber") }
    }
}