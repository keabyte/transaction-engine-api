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

    fun createTransaction(params: CreateTransactionParameters): TransactionEventEntity {
        val transaction = TransactionEventEntity(
            transactionType = params.transactionType
        )

        val accountNumbers = params.investments.map { it.accountNumber }.toSet()

        for (accountNumber in accountNumbers) {
            val account = accountService.getAccountById(accountNumber)
            val accountTransaction = AccountTransactionEntity(
                transactionEvent = transaction,
                account = account
            )
            transaction.accountTransactions.add(accountTransaction)

            for (investment in params.investments.filter { it.accountNumber.equals(accountNumber) }) {
                val investmentTransaction = InvestmentTransactionEntity(
                    accountTransaction = accountTransaction,
                    amount = investment.amount,
                    currency = investment.currency,
                )
                accountTransaction.investmentTransactions.add(investmentTransaction)
            }
        }


        return transaction
    }

    fun createDeposit(request: CreateDepositRequest): TransactionEventEntity {
        return transactionEventRepository.save(
            createTransaction(
                CreateTransactionParameters(
                    transactionType = TransactionType.DEPOSIT,
                    investments = listOf(
                        CreateInvestmentParameters(
                            request.accountNumber, request.amount, request.currency
                        )
                    )
                )
            )
        )
    }
}