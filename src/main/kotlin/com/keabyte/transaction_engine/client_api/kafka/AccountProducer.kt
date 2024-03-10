package com.keabyte.transaction_engine.client_api.kafka

import com.keabyte.transaction_engine.client_api.web.model.account.Account
import io.micronaut.configuration.kafka.annotation.KafkaClient
import io.micronaut.configuration.kafka.annotation.KafkaKey
import io.micronaut.configuration.kafka.annotation.Topic

@KafkaClient
interface AccountProducer {

    @Topic("accounts")
    fun sendClient(@KafkaKey accountNumber: String, account: Account)
}