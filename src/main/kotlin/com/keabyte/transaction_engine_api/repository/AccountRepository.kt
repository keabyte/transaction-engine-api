package com.keabyte.transaction_engine_api.repository

import com.keabyte.transaction_engine_api.repository.entity.AccountEntity
import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.CrudRepository

@Repository
interface AccountRepository : CrudRepository<AccountEntity, Long>