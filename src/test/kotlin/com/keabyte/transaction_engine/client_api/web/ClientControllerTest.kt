package com.keabyte.transaction_engine.client_api.web

import com.keabyte.transaction_engine.client_api.exception.BusinessException
import com.keabyte.transaction_engine.client_api.fixture.ClientFixture
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import jakarta.validation.ConstraintViolationException
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

@MicronautTest
class ClientControllerTest(private val clientController: ClientController) {

    @Test
    fun `create client`() {
        val request = ClientFixture.createClientRequest_john()
        val client = clientController.createClient(request)

        assertNotNull(client.clientNumber)

        Assertions.assertThat(client)
            .usingRecursiveComparison()
            .ignoringFields("clientNumber")
            .isEqualTo(request)
    }

    @Test
    fun `get client`() {
        val client1 = clientController.createClient(ClientFixture.createClientRequest_jane())
        val client2 = clientController.getClientById(client1.clientNumber)

        Assertions.assertThat(client2)
            .usingRecursiveComparison()
            .isEqualTo(client1)
    }

    @Test
    fun `get client that does not exist`() {
        assertThrows<com.keabyte.transaction_engine.client_api.exception.BusinessException> {
            clientController.getClientById(
                "-1"
            )
        }
    }

    @Test
    fun `get client with blank client number`() {
        assertThrows<ConstraintViolationException> { clientController.getClientById("") }
    }
}