package com.keabyte.transaction_engine_api.repository

import com.keabyte.transaction_engine_api.repository.entity.transaction.TransactionEventEntity
import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.CrudRepository

@Repository
interface TransactionEventRepository : CrudRepository<TransactionEventEntity, Long>