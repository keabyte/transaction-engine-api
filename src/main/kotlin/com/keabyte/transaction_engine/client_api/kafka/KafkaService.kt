package com.keabyte.transaction_engine.client_api.kafka

import io.micronaut.data.model.Pageable
import io.micronaut.scheduling.annotation.Scheduled
import io.micronaut.serde.ObjectMapper
import jakarta.inject.Singleton
import jakarta.transaction.Transactional
import net.javacrumbs.shedlock.micronaut.SchedulerLock
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.time.OffsetDateTime

@Singleton
open class KafkaService(
    private val objectMapper: ObjectMapper, private val kafkaProducer: KafkaProducer,
    private val outboundMessageRepository: OutboundMessageRepository,
) {

    private val log: Logger = LoggerFactory.getLogger(this::class.java)
    private val MAX_MESSAGE_AGE_DAYS = 1L

    fun <T> saveMessage(topic: String, key: String, obj: T): OutboundMessage {
        val data = objectMapper.writeValueAsString(obj)
        val message = OutboundMessage(
            data = data,
            key = key,
            topic = topic,
        )

        return outboundMessageRepository.save(message)
    }

    @Transactional
    open fun sendMessage(message: OutboundMessage) {
        log.info("Publishing event message to topic '${message.topic}' with key '${message.key}' and message ${message.data}")
        kafkaProducer.publish(message.topic, message.key, message.data)
        message.status = OutboundMessageStatus.SENT
        outboundMessageRepository.update(message)
    }

    @Scheduled(fixedDelay = "10s", initialDelay = "10s")
    @SchedulerLock(name = "KafkaService_publishPendingMessages", lockAtMostFor = "5m")
    open fun publishPendingMessagesJob() {
        publishPendingMessages()
    }

    fun publishPendingMessages() {
        val pendingEvents = outboundMessageRepository.findAllByStatusOrderByCreatedDateAsc(
            OutboundMessageStatus.PENDING,
            Pageable.from(0, 500)
        )
        if (pendingEvents.isNotEmpty()) {
            log.info("Event message publishing job found ${pendingEvents.size} pending message(s) to publish")
        }

        pendingEvents.forEach {
            try {
                sendMessage(it)
            } catch (e: Exception) {
                log.error("Failed to publish event message (id '${it.id}', topic '${it.topic}')", e)
            }
        }
    }

    @Scheduled(fixedDelay = "10m", initialDelay = "10s")
    @SchedulerLock(name = "KafkaService_deleteSentMessages", lockAtMostFor = "5m")
    open fun deleteSentMessagesJob() {
        deleteSentMessages()
    }

    fun deleteSentMessages() {
        val sentMessages = outboundMessageRepository.findAllByStatusAndCreatedDateBeforeOrderByCreatedDateAsc(
            OutboundMessageStatus.SENT,
            OffsetDateTime.now().minusDays(MAX_MESSAGE_AGE_DAYS),
            Pageable.from(0, 500)
        )

        if (sentMessages.isNotEmpty()) {
            log.info("Event message cleanup job found ${sentMessages.size} sent message(s) to delete")
        }

        outboundMessageRepository.deleteAll(sentMessages)
    }
}