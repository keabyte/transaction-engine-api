package com.keabyte.transaction_engine.client_api.kafka

import jakarta.persistence.Entity
import jakarta.persistence.EnumType.STRING
import jakarta.persistence.Enumerated
import jakarta.persistence.Id
import org.hibernate.annotations.CreationTimestamp
import java.time.OffsetDateTime
import java.util.*

@Entity(name = "outbound_message")
data class OutboundMessage(
    @Id val id: String = UUID.randomUUID().toString(),
    @CreationTimestamp
    val createdDate: OffsetDateTime? = null,
    val topic: String,
    val key: String,
    val data: String,
    @Enumerated(STRING)
    var status: OutboundMessageStatus = OutboundMessageStatus.PENDING,
)