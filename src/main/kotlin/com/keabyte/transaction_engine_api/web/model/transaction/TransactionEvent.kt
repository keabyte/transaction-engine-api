package com.keabyte.transaction_engine_api.web.model.transaction

import com.keabyte.transaction_engine_api.repository.enum.TransactionType
import io.micronaut.serde.annotation.Serdeable
import java.time.OffsetDateTime

@Serdeable
data class TransactionEvent(
    val transactionReference: String,
    val dateCreated: OffsetDateTime,
    val transactionType: TransactionType,
    val accountTransactions: List<AccountTransaction>
)