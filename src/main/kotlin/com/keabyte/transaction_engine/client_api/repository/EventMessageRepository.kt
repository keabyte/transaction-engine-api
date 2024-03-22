package com.keabyte.transaction_engine.client_api.repository

import com.keabyte.transaction_engine.client_api.kafka.EventMessageStatus
import com.keabyte.transaction_engine.client_api.repository.entity.EventMessageEntity
import io.micronaut.data.annotation.Repository
import io.micronaut.data.model.Pageable
import io.micronaut.data.repository.CrudRepository
import java.time.OffsetDateTime

@Repository
interface EventMessageRepository : CrudRepository<EventMessageEntity, String> {

    fun findAllByStatusOrderByCreatedDateAsc(status: EventMessageStatus, pageable: Pageable): List<EventMessageEntity>

    fun findAllByStatusAndCreatedDateBeforeOrderByCreatedDateAsc(
        status: EventMessageStatus,
        createdDate: OffsetDateTime,
        pageable: Pageable
    ): List<EventMessageEntity>
}