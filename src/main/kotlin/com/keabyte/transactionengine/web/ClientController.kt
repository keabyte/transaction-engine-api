package com.keabyte.transactionengine.web

import com.keabyte.transactionengine.service.ClientService
import com.keabyte.transactionengine.web.model.client.Client
import com.keabyte.transactionengine.web.model.client.CreateClientRequest
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.*
import reactor.core.publisher.Mono
import java.util.*

@Controller("/clients")
@Produces(MediaType.APPLICATION_JSON)
class ClientController(
    private val clientService: ClientService
) {

    @Get("/{id}")
    fun getClientById(id: UUID): Mono<Client> {
        return clientService.getClientById(id)
    }

    @Post
    fun createClient(@Body createClientRequest: CreateClientRequest): Mono<Client> {
        return clientService.createClient(createClientRequest)
    }
}