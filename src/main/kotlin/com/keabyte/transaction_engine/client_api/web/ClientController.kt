package com.keabyte.transaction_engine.client_api.web

import com.keabyte.transaction_engine.client_api.service.ClientService
import com.keabyte.transaction_engine.client_api.web.model.client.Client
import com.keabyte.transaction_engine.client_api.web.model.client.CreateClientRequest
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.*
import io.swagger.v3.oas.annotations.tags.Tag
import java.util.*

@Tag(name = "Client")
@Controller("/clients")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class ClientController(
    private val clientService: ClientService
) {

    @Get("/{clientNumber}")
    fun getClientById(clientNumber: String): Client {
        return clientService.getClientById(clientNumber)
    }

    @Post
    fun createClient(@Body createClientRequest: CreateClientRequest): Client {
        return clientService.createClient(createClientRequest)
    }
}