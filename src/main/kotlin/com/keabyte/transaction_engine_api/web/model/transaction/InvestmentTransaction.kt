package com.keabyte.transaction_engine_api.web.model.transaction

import com.keabyte.transaction_engine_api.repository.enum.BalanceEffectType
import java.math.BigDecimal

class InvestmentTransaction(val amount: BigDecimal, val currency: String, val balanceEffectType: BalanceEffectType)