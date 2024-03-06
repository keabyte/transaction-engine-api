package com.keabyte.transaction_engine.client_api.service

import com.keabyte.transaction_engine.client_api.repository.TransactionEventRepository
import jakarta.inject.Singleton
import java.math.BigDecimal

@Singleton
class AccountBalanceService(
    private val accountService: AccountService,
    private val transactionEventRepository: com.keabyte.transaction_engine.client_api.repository.TransactionEventRepository
) {

    fun getAccountBalance(accountNumber: String): BigDecimal {
        val account = accountService.getAccountById(accountNumber)
        return transactionEventRepository.calculateAccountBalance(account.accountNumber)
    }
}