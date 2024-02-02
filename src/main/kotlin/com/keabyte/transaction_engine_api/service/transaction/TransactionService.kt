package com.keabyte.transaction_engine_api.service.transaction

import com.keabyte.transaction_engine_api.repository.TransactionEventRepository
import com.keabyte.transaction_engine_api.repository.entity.transaction.AccountTransactionEntity
import com.keabyte.transaction_engine_api.repository.entity.transaction.InvestmentTransactionEntity
import com.keabyte.transaction_engine_api.repository.entity.transaction.TransactionEventEntity
import com.keabyte.transaction_engine_api.repository.enum.TransactionType
import com.keabyte.transaction_engine_api.service.AccountService
import com.keabyte.transaction_engine_api.web.model.transaction.CreateDepositRequest
import jakarta.inject.Singleton

@Singleton
class TransactionService(
    private val transactionEventRepository: TransactionEventRepository,
    private val accountService: AccountService
) {

    fun createDeposit(request: CreateDepositRequest): TransactionEventEntity {
        val account = accountService.getAccountById(request.accountNumber)

        val transactionEvent = TransactionEventEntity(
            transactionType = TransactionType.DEPOSIT
        )
        val accountTransaction = AccountTransactionEntity(
            transactionEvent = transactionEvent,
            account = account
        )
        val investmentTransaction =
            InvestmentTransactionEntity(
                accountTransaction = accountTransaction,
                amount = request.amount,
                currency = request.currency
            )

        transactionEvent.accountTransactions.add(accountTransaction)
        accountTransaction.investmentTransactions.add(investmentTransaction)

        return transactionEventRepository.save(transactionEvent)
    }
}