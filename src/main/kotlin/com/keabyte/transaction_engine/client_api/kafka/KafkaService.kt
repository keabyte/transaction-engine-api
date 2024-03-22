package com.keabyte.transaction_engine.client_api.kafka

import com.keabyte.transaction_engine.client_api.repository.EventMessageRepository
import com.keabyte.transaction_engine.client_api.repository.entity.EventMessageEntity
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
    private val eventMessageRepository: EventMessageRepository,
) {

    private val log: Logger = LoggerFactory.getLogger(this::class.java)
    private val MAX_MESSAGE_AGE_DAYS = 1L

    fun <T> saveMessage(topic: String, key: String, obj: T): EventMessageEntity {
        val data = objectMapper.writeValueAsString(obj)
        val eventMessage = EventMessageEntity(
            data = data,
            key = key,
            topic = topic,
        )

        return eventMessageRepository.save(eventMessage)
    }

    @Transactional
    open fun sendMessage(eventMessage: EventMessageEntity) {
        log.info("Publishing event message to topic '${eventMessage.topic}' with key '${eventMessage.key}' and message ${eventMessage.data}")
        kafkaProducer.publish(eventMessage.topic, eventMessage.key, eventMessage.data)
        eventMessage.status = EventMessageStatus.SENT
        eventMessageRepository.update(eventMessage)
    }

    @Scheduled(fixedDelay = "10s", initialDelay = "10s")
    @SchedulerLock(name = "KafkaService_publishPendingMessages")
    open fun publishPendingMessages() {
        val pendingMessages = eventMessageRepository.findAllByStatusOrderByCreatedDateAsc(
            EventMessageStatus.PENDING,
            Pageable.from(0, 500)
        )
        if (pendingMessages.isNotEmpty()) {
            log.info("Event message publishing job found ${pendingMessages.size} pending message(s) to publish")
        }

        pendingMessages.forEach {
            try {
                sendMessage(it)
            } catch (e: Exception) {
                log.error("Failed to publish event message (id '${it.id}', topic '${it.topic}')", e)
            }
        }
    }

    @Scheduled(fixedDelay = "10m", initialDelay = "10s")
    fun deleteSentMessages() {
        val sentMessages = eventMessageRepository.findAllByStatusAndCreatedDateBeforeOrderByCreatedDateAsc(
            EventMessageStatus.SENT,
            OffsetDateTime.now().minusDays(MAX_MESSAGE_AGE_DAYS),
            Pageable.from(0, 500)
        )

        if (sentMessages.isNotEmpty()) {
            log.info("Event message cleanup job found ${sentMessages.size} sent message(s) to delete")
        }

        eventMessageRepository.deleteAll(sentMessages)
    }
}