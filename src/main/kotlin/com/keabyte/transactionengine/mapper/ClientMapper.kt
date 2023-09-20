package com.keabyte.transactionengine.mapper

import com.keabyte.transactionengine.entity.ClientEntity
import com.keabyte.transactionengine.web.model.Client
import com.keabyte.transactionengine.web.model.CreateClientRequest
import org.mapstruct.Mapper
import org.mapstruct.Mapping


@Mapper(componentModel = "jsr330")
interface ClientMapper {

    fun map(source: ClientEntity): Client

    @Mapping(target = "id", expression = MapperUtils.GENERATE_UUID_EXPRESSION)
    fun map(source: CreateClientRequest): ClientEntity
}