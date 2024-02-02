package com.keabyte.transaction_engine_api.service.transaction

import java.math.BigDecimal

data class CreateInvestmentParameters(val accountNumber: String, val amount: BigDecimal, val currency: String)