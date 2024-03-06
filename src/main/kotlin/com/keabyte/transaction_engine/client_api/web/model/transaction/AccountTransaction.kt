package com.keabyte.transaction_engine.client_api.web.model.transaction

import io.micronaut.serde.annotation.Serdeable

@Serdeable
data class AccountTransaction(val accountNumber: String, val invesmentTransactions: List<InvestmentTransaction>)