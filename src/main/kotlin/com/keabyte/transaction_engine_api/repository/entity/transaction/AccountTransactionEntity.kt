package com.keabyte.transaction_engine_api.repository.entity.transaction

import com.keabyte.transaction_engine_api.repository.entity.AccountEntity
import com.keabyte.transaction_engine_api.web.model.transaction.AccountTransaction
import jakarta.persistence.*

@Entity(name = "account_transaction")
data class AccountTransactionEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long? = null,
    @ManyToOne
    @JoinColumn(name = "transaction_event_id")
    val transactionEvent: TransactionEventEntity,
    @ManyToOne
    @JoinColumn(name = "account_id")
    val account: AccountEntity,
    @OneToMany(mappedBy = "accountTransaction", cascade = [CascadeType.ALL])
    var investmentTransactions: MutableList<InvestmentTransactionEntity> = ArrayList()
) {

    fun toModel() = AccountTransaction(
        accountNumber = account.accountNumber,
        invesmentTransactions = investmentTransactions.map { it.toModel() })
}