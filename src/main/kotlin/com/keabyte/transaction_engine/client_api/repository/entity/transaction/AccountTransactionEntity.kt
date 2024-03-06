package com.keabyte.transaction_engine.client_api.repository.entity.transaction

import com.keabyte.transaction_engine.client_api.repository.entity.AccountEntity
import com.keabyte.transaction_engine.client_api.web.model.transaction.AccountTransaction
import jakarta.persistence.*

@Entity(name = "account_transaction")
data class AccountTransactionEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long? = null,
    @ManyToOne
    @JoinColumn(name = "transaction_event_id")
    val transactionEvent: com.keabyte.transaction_engine.client_api.repository.entity.transaction.TransactionEventEntity,
    @ManyToOne
    @JoinColumn(name = "account_id")
    val account: com.keabyte.transaction_engine.client_api.repository.entity.AccountEntity,
    @OneToMany(mappedBy = "accountTransaction", cascade = [CascadeType.ALL])
    var investmentTransactions: MutableList<com.keabyte.transaction_engine.client_api.repository.entity.transaction.InvestmentTransactionEntity> = ArrayList()
) {

    fun toModel() = AccountTransaction(
        accountNumber = account.accountNumber,
        invesmentTransactions = investmentTransactions.map { it.toModel() })
}