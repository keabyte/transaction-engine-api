package com.keabyte.transaction_engine.client_api.service

import com.keabyte.transaction_engine.client_api.exception.BusinessException
import com.keabyte.transaction_engine.client_api.kafka.AccountProducer
import com.keabyte.transaction_engine.client_api.mapper.AccountMapper
import com.keabyte.transaction_engine.client_api.repository.AccountRepository
import com.keabyte.transaction_engine.client_api.repository.entity.AccountEntity
import com.keabyte.transaction_engine.client_api.web.model.account.CreateAccountRequest
import jakarta.inject.Singleton
import jakarta.validation.constraints.NotBlank

@Singleton
open class AccountService(
    private val accountRepository: AccountRepository,
    private val accountMapper: AccountMapper,
    private val accountProducer: AccountProducer
) {

    fun createAccount(request: CreateAccountRequest): AccountEntity {
        val accountEntity = accountRepository.save(accountMapper.mapCreateAccountRequestToAccountEntity(request))
        accountProducer.sendClient(accountEntity.accountNumber, accountEntity.toModel())
        return accountEntity
    }

    open fun getAccountById(@NotBlank accountNumber: String): AccountEntity {
        return accountRepository.findByAccountNumber(accountNumber)
            .orElseThrow { BusinessException("No account exists with account number $accountNumber") }
    }
}