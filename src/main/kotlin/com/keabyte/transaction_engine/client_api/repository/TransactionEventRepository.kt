package com.keabyte.transaction_engine.client_api.repository

import com.keabyte.transaction_engine.client_api.repository.entity.transaction.TransactionEventEntity
import io.micronaut.data.annotation.Query
import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository
import java.math.BigDecimal

@Repository
interface TransactionEventRepository : JpaRepository<TransactionEventEntity, Long> {

    @Query(
        value = """
        SELECT COALESCE(SUM(amount), 0) as accountBalance
        FROM investment_transaction itrn
        INNER JOIN account_transaction actr on actr.id = itrn.account_transaction_id
        INNER JOIN account acct on acct.id = actr.account_id
        WHERE acct.account_number = :accountNumber
    """, nativeQuery = true
    )
    fun calculateAccountBalance(accountNumber: String): BigDecimal
}