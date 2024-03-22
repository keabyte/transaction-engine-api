package com.keabyte.transaction_engine.client_api.kafka

import io.micronaut.configuration.kafka.annotation.KafkaClient
import io.micronaut.configuration.kafka.annotation.KafkaKey
import io.micronaut.configuration.kafka.annotation.Topic

@KafkaClient
interface KafkaProducer {

    @Topic("accounts")
    fun publish(@Topic topic: String, @KafkaKey accountNumber: String, data: String)
}