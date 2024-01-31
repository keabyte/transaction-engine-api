package com.keabyte.transaction_engine_api.web

import com.keabyte.transaction_engine_api.web.model.client.CreateClientRequest
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

@MicronautTest
class ClientControllerTest(private val clientController: ClientController) {

    @Test
    fun `create client`() {
        val request = CreateClientRequest(firstName = "John", lastName = "Smith")
        val client = clientController.createClient(request)
        
        assertNotNull(client.id)
        assertTrue(client.id > 0)
        assertEquals(request.firstName, client.firstName)
        assertEquals(request.lastName, client.lastName)
    }

    @Test
    fun `get client`() {
        val client1 = clientController.createClient(CreateClientRequest(firstName = "Jane", lastName = "Doe"))
        val client2 = clientController.getClientById(client1.id)

        assertEquals(client1.id, client2.id)
    }
}