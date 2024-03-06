package com.keabyte.transaction_engine.client_api.service.transaction

import com.keabyte.transaction_engine.client_api.repository.enum.BalanceEffectType
import java.math.BigDecimal

data class CreateInvestmentParameters(
    val accountNumber: String,
    val amount: BigDecimal,
    val currency: String,
    val balanceEffectType: BalanceEffectType
)