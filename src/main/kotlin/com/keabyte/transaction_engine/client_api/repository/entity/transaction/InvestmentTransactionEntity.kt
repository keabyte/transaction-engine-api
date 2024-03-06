package com.keabyte.transaction_engine.client_api.repository.entity.transaction

import com.keabyte.transaction_engine.client_api.repository.enum.BalanceEffectType
import com.keabyte.transaction_engine.client_api.web.model.transaction.InvestmentTransaction
import jakarta.persistence.*
import java.math.BigDecimal

@Entity(name = "investment_transaction")
data class InvestmentTransactionEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long? = null,
    @ManyToOne
    @JoinColumn(name = "account_transaction_id")
    val accountTransaction: com.keabyte.transaction_engine.client_api.repository.entity.transaction.AccountTransactionEntity,
    val amount: BigDecimal,
    val currency: String,
    @Enumerated(EnumType.STRING)
    val balanceEffectType: BalanceEffectType
) {
    fun toModel() = InvestmentTransaction(amount = amount, currency = currency, balanceEffectType = balanceEffectType)
}