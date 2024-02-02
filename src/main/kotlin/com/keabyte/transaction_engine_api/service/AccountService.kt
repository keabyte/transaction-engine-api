package com.keabyte.transaction_engine_api.service

import com.keabyte.transaction_engine_api.exception.BusinessException
import com.keabyte.transaction_engine_api.mapper.AccountMapper
import com.keabyte.transaction_engine_api.repository.AccountRepository
import com.keabyte.transaction_engine_api.repository.entity.AccountEntity
import com.keabyte.transaction_engine_api.web.model.account.CreateAccountRequest
import jakarta.inject.Singleton

@Singleton
class AccountService(private val accountRepository: AccountRepository, private val accountMapper: AccountMapper) {

    fun createAccount(request: CreateAccountRequest): AccountEntity {
        val accountEntity = accountMapper.mapCreateAccountRequestToAccountEntity(request)
        return accountRepository.save(accountEntity)
    }

    fun getAccountById(accountNumber: String): AccountEntity {
        return accountRepository.findByAccountNumber(accountNumber)
            .orElseThrow { BusinessException("No account exists with account number $accountNumber") }
    }
}