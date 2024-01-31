package com.keabyte.transaction_engine_api.web

import com.keabyte.transaction_engine_api.service.ClientService
import com.keabyte.transaction_engine_api.web.model.client.Client
import com.keabyte.transaction_engine_api.web.model.client.CreateClientRequest
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.*
import java.util.*

@Controller("/clients")
@Produces(MediaType.APPLICATION_JSON)
class ClientController(
    private val clientService: ClientService
) {

    @Get("/{id}")
    fun getClientById(id: Long): Client {
        return clientService.getClientById(id)
    }

    @Post
    fun createClient(@Body createClientRequest: CreateClientRequest): Client {
        return clientService.createClient(createClientRequest)
    }
}