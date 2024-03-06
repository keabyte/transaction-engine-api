package com.keabyte.transaction_engine.client_api.service.transaction

import com.keabyte.transaction_engine.client_api.exception.BusinessException
import com.keabyte.transaction_engine.client_api.repository.TransactionEventRepository
import com.keabyte.transaction_engine.client_api.repository.entity.transaction.AccountTransactionEntity
import com.keabyte.transaction_engine.client_api.repository.entity.transaction.InvestmentTransactionEntity
import com.keabyte.transaction_engine.client_api.repository.entity.transaction.TransactionEventEntity
import com.keabyte.transaction_engine.client_api.repository.enum.BalanceEffectType
import com.keabyte.transaction_engine.client_api.repository.enum.TransactionType
import com.keabyte.transaction_engine.client_api.service.AccountBalanceService
import com.keabyte.transaction_engine.client_api.service.AccountService
import com.keabyte.transaction_engine.client_api.web.model.transaction.CreateDepositRequest
import com.keabyte.transaction_engine.client_api.web.model.transaction.CreateWithdrawalRequest
import jakarta.inject.Singleton
import java.math.BigDecimal

@Singleton
class TransactionService(
    private val transactionEventRepository: com.keabyte.transaction_engine.client_api.repository.TransactionEventRepository,
    private val accountService: AccountService,
    private val accountBalanceService: AccountBalanceService
) {

    fun createTransaction(params: CreateTransactionParameters): TransactionEventEntity {
        val transaction = TransactionEventEntity(
            type = params.transactionType
        )

        val accountNumbers = params.investments.map { it.accountNumber }.toSet()

        for (accountNumber in accountNumbers) {
            val account = accountService.getAccountById(accountNumber)
            val accountTransaction =
                com.keabyte.transaction_engine.client_api.repository.entity.transaction.AccountTransactionEntity(
                    transactionEvent = transaction,
                    account = account
                )
            transaction.accountTransactions.add(accountTransaction)

            for (investment in params.investments.filter { it.accountNumber.equals(accountNumber) }) {
                val investmentTransaction = InvestmentTransactionEntity(
                    accountTransaction = accountTransaction,
                    amount = investment.amount,
                    currency = investment.currency,
                    balanceEffectType = investment.balanceEffectType
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
                            accountNumber = request.accountNumber,
                            amount = request.amount,
                            currency = request.currency,
                            balanceEffectType = BalanceEffectType.CREDIT
                        )
                    )
                )
            )
        )
    }

    fun createWithdrawal(request: CreateWithdrawalRequest): TransactionEventEntity {
        val accountBalance = accountBalanceService.getAccountBalance(request.accountNumber)
        val newBalance = accountBalance - request.amount
        if (newBalance < BigDecimal.ZERO) {
            throw com.keabyte.transaction_engine.client_api.exception.BusinessException("Failed to create withdrawal for account number ${request.accountNumber}. The request amount of ${request.amount} would leave the account in negative balance (new balance $newBalance).")
        }

        return transactionEventRepository.save(
            createTransaction(
                CreateTransactionParameters(
                    transactionType = TransactionType.WITHDRAWAL,
                    investments = listOf(
                        CreateInvestmentParameters(
                            accountNumber = request.accountNumber,
                            amount = request.amount,
                            currency = request.currency,
                            balanceEffectType = BalanceEffectType.DEBIT
                        )
                    )
                )
            )
        )
    }
}