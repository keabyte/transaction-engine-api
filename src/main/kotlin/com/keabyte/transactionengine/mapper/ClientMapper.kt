package com.keabyte.transactionengine.mapper

import com.keabyte.transactionengine.entity.ClientEntity
import com.keabyte.transactionengine.web.model.Client
import org.mapstruct.Mapper

@Mapper(componentModel = "jsr330")
interface ClientMapper {

    fun map(source: ClientEntity): Client
}