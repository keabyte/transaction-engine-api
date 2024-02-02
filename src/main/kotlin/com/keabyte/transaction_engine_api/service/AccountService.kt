package com.keabyte.transaction_engine_api.service

import com.keabyte.transaction_engine_api.exception.BusinessException
import com.keabyte.transaction_engine_api.mapper.AccountMapper
import com.keabyte.transaction_engine_api.repository.AccountRepository
import com.keabyte.transaction_engine_api.repository.entity.AccountEntity
import com.keabyte.transaction_engine_api.web.model.account.CreateAccountRequest
import jakarta.inject.Singleton
import jakarta.validation.constraints.NotBlank

@Singleton
open class AccountService(
    private val accountRepository: AccountRepository,
    private val accountMapper: AccountMapper,
) {

    fun createAccount(request: CreateAccountRequest): AccountEntity {
        val accountEntity = accountMapper.mapCreateAccountRequestToAccountEntity(request)
        return accountRepository.save(accountEntity)
    }

    open fun getAccountById(@NotBlank accountNumber: String): AccountEntity {
        return accountRepository.findByAccountNumber(accountNumber)
            .orElseThrow { BusinessException("No account exists with account number $accountNumber") }
    }
}