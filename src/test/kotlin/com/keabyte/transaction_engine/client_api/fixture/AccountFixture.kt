package com.keabyte.transaction_engine.client_api.fixture

import com.keabyte.transaction_engine.client_api.web.model.account.Account
import java.time.OffsetDateTime
import java.util.*

class AccountFixture {

    companion object {
        fun account() = Account(
            accountNumber = UUID.randomUUID().toString(),
            clientNumber = UUID.randomUUID().toString(),
            createdDate = OffsetDateTime.now()
        )
    }
}