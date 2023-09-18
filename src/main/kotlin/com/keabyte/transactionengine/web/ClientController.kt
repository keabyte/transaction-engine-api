package com.keabyte.transactionengine.web

import com.keabyte.transactionengine.service.ClientService
import com.keabyte.transactionengine.web.model.Client
import com.keabyte.transactionengine.web.model.CreateClientRequest
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import io.micronaut.http.annotation.Produces
import reactor.core.publisher.Mono

@Controller("/clients")
@Produces(MediaType.APPLICATION_JSON)
class ClientController(
    private val clientService: ClientService
) {

    @Get("/{id}")
    fun getClientById(id: Long): Mono<Client> {
        return clientService.getClientById(id);
    }

    @Post
    fun createClient(): Mono<Client> {
        return clientService.createClient(CreateClientRequest())
    }
}