package com.keabyte.transaction_engine.client_api.repository

import com.keabyte.transaction_engine.client_api.repository.entity.AccountEntity
import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.CrudRepository
import java.util.*

@Repository
interface AccountRepository :
    CrudRepository<AccountEntity, Long> {

    fun findByAccountNumber(accountNumber: String): Optional<AccountEntity>
}