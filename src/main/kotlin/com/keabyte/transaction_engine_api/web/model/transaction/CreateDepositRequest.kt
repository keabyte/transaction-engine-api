package com.keabyte.transaction_engine_api.web.model.transaction

import io.micronaut.serde.annotation.Serdeable
import java.math.BigDecimal

@Serdeable
data class CreateDepositRequest(val accountNumber: String, val amount: BigDecimal, val currency: String)