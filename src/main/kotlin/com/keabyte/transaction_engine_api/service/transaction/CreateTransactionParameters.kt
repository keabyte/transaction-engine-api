package com.keabyte.transaction_engine_api.service.transaction

import com.keabyte.transaction_engine_api.repository.enum.TransactionType


data class CreateTransactionParameters(
    val transactionType: TransactionType,
    val investments: List<CreateInvestmentParameters>
)