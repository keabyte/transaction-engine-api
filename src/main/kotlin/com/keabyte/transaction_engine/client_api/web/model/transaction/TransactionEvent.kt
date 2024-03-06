package com.keabyte.transaction_engine.client_api.web.model.transaction

import com.keabyte.transaction_engine.client_api.repository.enum.TransactionType
import io.micronaut.serde.annotation.Serdeable
import java.time.OffsetDateTime

@Serdeable
data class TransactionEvent(
    val transactionReference: String,
    val dateCreated: OffsetDateTime,
    val type: TransactionType,
    val accountTransactions: List<AccountTransaction>
)