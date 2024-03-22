package com.keabyte.transaction_engine.client_api.repository

import com.keabyte.transaction_engine.client_api.kafka.OutboundMessageStatus
import com.keabyte.transaction_engine.client_api.repository.entity.OutboundMessage
import io.micronaut.data.annotation.Repository
import io.micronaut.data.model.Pageable
import io.micronaut.data.repository.CrudRepository
import java.time.OffsetDateTime

@Repository
interface OutboundMessageRepository : CrudRepository<OutboundMessage, String> {

    fun findAllByStatusOrderByCreatedDateAsc(status: OutboundMessageStatus, pageable: Pageable): List<OutboundMessage>

    fun findAllByStatusAndCreatedDateBeforeOrderByCreatedDateAsc(
        status: OutboundMessageStatus,
        createdDate: OffsetDateTime,
        pageable: Pageable
    ): List<OutboundMessage>
}