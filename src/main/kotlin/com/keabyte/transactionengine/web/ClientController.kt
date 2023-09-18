package com.keabyte.transactionengine.web

import com.keabyte.transactionengine.entity.ClientEntity
import com.keabyte.transactionengine.repository.ClientRepository
import com.keabyte.transactionengine.web.model.CreateClientRequest
import io.micronaut.core.annotation.NonNull
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import io.micronaut.http.annotation.Produces
import reactor.core.publisher.Mono

@Controller("/clients")
@Produces(MediaType.APPLICATION_JSON)
class ClientController(
    private val clientRepository: ClientRepository
) {

    @Get("/{id}")
    fun getClientById(id: Long): Mono<ClientEntity> {
        return clientRepository.findById(id)
    }

    @Post
    fun createClient(): Mono<ClientEntity> {
        return clientRepository.save(ClientEntity(1L))
    }
}