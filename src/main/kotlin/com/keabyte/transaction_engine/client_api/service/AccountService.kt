package com.keabyte.transaction_engine.client_api.service

import com.keabyte.transaction_engine.client_api.exception.BusinessException
import com.keabyte.transaction_engine.client_api.kafka.KafkaService
import com.keabyte.transaction_engine.client_api.mapper.AccountMapper
import com.keabyte.transaction_engine.client_api.repository.AccountRepository
import com.keabyte.transaction_engine.client_api.repository.entity.AccountEntity
import com.keabyte.transaction_engine.client_api.web.model.account.Account
import com.keabyte.transaction_engine.client_api.web.model.account.CreateAccountRequest
import io.micronaut.context.annotation.Property
import jakarta.inject.Singleton
import jakarta.validation.constraints.NotBlank

@Singleton
open class AccountService(
    private val accountRepository: AccountRepository,
    private val accountMapper: AccountMapper,
    private val kafkaService: KafkaService,
    @Property(name = "kafka.producer.topic.accounts.name") private val accountTopicName: String
) {

    fun createAccount(request: CreateAccountRequest): AccountEntity {
        val accountEntity = accountRepository.save(accountMapper.mapCreateAccountRequestToAccountEntity(request))
        publishAccount(accountEntity.toModel())
        return accountEntity
    }

    open fun getAccountById(@NotBlank accountNumber: String): AccountEntity {
        return accountRepository.findByAccountNumber(accountNumber)
            .orElseThrow { BusinessException("No account exists with account number $accountNumber") }
    }

    private fun publishAccount(accountEntity: Account) {
        kafkaService.saveMessage(accountTopicName, accountEntity.accountNumber, accountEntity)
    }
}