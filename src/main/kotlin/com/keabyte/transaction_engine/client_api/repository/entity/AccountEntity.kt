package com.keabyte.transaction_engine.client_api.repository.entity

import com.keabyte.transaction_engine.client_api.type.AccountType
import com.keabyte.transaction_engine.client_api.web.model.account.Account
import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import java.time.OffsetDateTime
import java.util.*

@Entity(name = "account")
data class AccountEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long? = null,
    val accountNumber: String = UUID.randomUUID().toString(),
    @CreationTimestamp
    val createdDate: OffsetDateTime? = null,
    @ManyToOne
    @JoinColumn(name = "client_id")
    val client: ClientEntity,
    @Enumerated(EnumType.STRING)
    val type: AccountType
) {

    fun toModel(): Account = Account(
        accountNumber = accountNumber,
        clientNumber = client.clientNumber,
        createdDate = createdDate!!,
        type = type
    )
}