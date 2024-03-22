package com.keabyte.transaction_engine.client_api.config

import io.micronaut.context.annotation.Factory
import io.micronaut.transaction.TransactionOperations
import jakarta.inject.Singleton
import net.javacrumbs.shedlock.core.LockProvider
import net.javacrumbs.shedlock.provider.jdbc.micronaut.MicronautJdbcLockProvider
import java.sql.Connection

@Factory
class ShedlockConfig {

    @Singleton
    fun lockProvider(transactionManager: TransactionOperations<Connection>): LockProvider {
        return MicronautJdbcLockProvider(transactionManager)
    }
}