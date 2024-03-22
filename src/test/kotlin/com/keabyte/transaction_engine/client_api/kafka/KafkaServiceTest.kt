package com.keabyte.transaction_engine.client_api.kafka

import com.keabyte.transaction_engine.client_api.fixture.AccountFixture
import io.micronaut.data.model.Pageable
import io.micronaut.serde.ObjectMapper
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.OffsetDateTime
import java.util.*

@MicronautTest
class KafkaServiceTest(
    private val outboundMessageRepository: OutboundMessageRepository,
    private val kafkaService: KafkaService,
    private val objectMapper: ObjectMapper,
    @PersistenceContext
    private val entityManager: EntityManager
) {

    @Test
    fun `save message`() {
        val account = AccountFixture.account()
        val result = kafkaService.saveMessage(
            "test-topic", UUID.randomUUID().toString(), account
        )

        assertThat(result.topic).isEqualTo("test-topic")
        assertThat(result.data).isEqualTo(objectMapper.writeValueAsString(account))

        val event = outboundMessageRepository.findById(result.id).get()
        assertThat(result).usingRecursiveComparison().isEqualTo(event)
    }

    @Test
    fun `publish pending message`() {
        val account = AccountFixture.account()
        kafkaService.saveMessage(
            "test-topic", UUID.randomUUID().toString(), account
        )
        val preCount = outboundMessageRepository.findAllByStatusOrderByCreatedDateAsc(
            OutboundMessageStatus.PENDING,
            Pageable.UNPAGED
        ).size
        assertThat(preCount).isGreaterThan(0)

        kafkaService.publishPendingMessages()

        val postCount = outboundMessageRepository.findAllByStatusOrderByCreatedDateAsc(
            OutboundMessageStatus.PENDING,
            Pageable.UNPAGED
        ).size
        assertThat(postCount).isEqualTo(0)
    }

    @Test
    fun `clean up sent messages`() {
        val message = outboundMessageRepository.save(
            OutboundMessage(
                topic = "test-topic",
                key = UUID.randomUUID().toString(),
                data = "test-data",
                status = OutboundMessageStatus.SENT,
            )
        )
        entityManager.createQuery("UPDATE outbound_message SET createdDate = :createdDate WHERE id = :id")
            .setParameter("createdDate", OffsetDateTime.now().minusDays(2))
            .setParameter("id", message.id)
            .executeUpdate()

        val preCount =
            outboundMessageRepository.findAllByStatusOrderByCreatedDateAsc(
                OutboundMessageStatus.SENT,
                Pageable.UNPAGED
            ).size
        assertThat(preCount).isGreaterThan(0)

        kafkaService.deleteSentMessages()

        val postCount =
            outboundMessageRepository.findAllByStatusOrderByCreatedDateAsc(
                OutboundMessageStatus.SENT,
                Pageable.UNPAGED
            ).size
        assertThat(postCount).isEqualTo(0)
    }

    @Test
    fun `clean up sent messages when message is not old enough`() {
        outboundMessageRepository.save(
            OutboundMessage(
                topic = "test-topic",
                key = UUID.randomUUID().toString(),
                data = "test-data",
                status = OutboundMessageStatus.SENT
            )
        )

        val preCount =
            outboundMessageRepository.findAllByStatusOrderByCreatedDateAsc(
                OutboundMessageStatus.SENT,
                Pageable.UNPAGED
            ).size
        assertThat(preCount).isEqualTo(1)

        kafkaService.deleteSentMessages()

        val postCount =
            outboundMessageRepository.findAllByStatusOrderByCreatedDateAsc(
                OutboundMessageStatus.SENT,
                Pageable.UNPAGED
            ).size
        assertThat(postCount).isEqualTo(1)
    }
}