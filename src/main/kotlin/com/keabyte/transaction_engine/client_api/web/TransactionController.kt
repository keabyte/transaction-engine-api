package com.keabyte.transaction_engine.client_api.web

import com.keabyte.transaction_engine.client_api.service.transaction.TransactionService
import com.keabyte.transaction_engine.client_api.web.model.transaction.CreateDepositRequest
import com.keabyte.transaction_engine.client_api.web.model.transaction.CreateWithdrawalRequest
import com.keabyte.transaction_engine.client_api.web.model.transaction.TransactionEvent
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post

@Controller("/transactions")
class TransactionController(private val transactionService: TransactionService) {
    @Post
    fun createDeposit(request: CreateDepositRequest): TransactionEvent {
        return transactionService.createDeposit(request).toModel()
    }

    @Post
    fun createWithdrawal(request: CreateWithdrawalRequest): TransactionEvent {
        return transactionService.createWithdrawal(request).toModel()
    }
}