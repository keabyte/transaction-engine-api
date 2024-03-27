package com.keabyte.transaction_engine.client_api.web

import com.keabyte.transaction_engine.client_api.exception.BusinessException
import com.keabyte.transaction_engine.client_api.fixture.ClientFixture
import com.keabyte.transaction_engine.client_api.web.model.client.CreateClientRequest
import io.micronaut.http.MediaType
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import io.restassured.specification.RequestSpecification
import jakarta.validation.ConstraintViolationException
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.hamcrest.Matchers.equalTo
import org.hamcrest.Matchers.notNullValue
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test

@MicronautTest
class ClientControllerTest(private val clientController: ClientController) {

    @Test
    fun `create client`() {
        val request = ClientFixture.createClientRequest_john()
        val client = clientController.createClient(request)

        assertNotNull(client.clientNumber)

        assertThat(client)
            .usingRecursiveComparison()
            .ignoringFields("clientNumber")
            .isEqualTo(request)
    }

    @Test
    fun `create client with blank first name`() {
        val request = CreateClientRequest(
            firstName = "",
            lastName = "Smith",
            dateOfBirth = ClientFixture.createClientRequest_john().dateOfBirth
        )
        assertThatThrownBy { clientController.createClient(request) }
            .isInstanceOf(ConstraintViolationException::class.java)
            .hasMessageContaining("firstName")
    }

    @Test
    fun `get client`() {
        val client1 = clientController.createClient(ClientFixture.createClientRequest_jane())
        val client2 = clientController.getClientById(client1.clientNumber)

        assertThat(client2)
            .usingRecursiveComparison()
            .isEqualTo(client1)
    }

    @Test
    fun `get client rest call`(spec: RequestSpecification) {
        val clientNumber = spec
            .contentType(MediaType.APPLICATION_JSON)
            .body(ClientFixture.createClientRequest_john())
            .`when`()
            .post("/clients")
            .then()
            .statusCode(200)
            .body("clientNumber", notNullValue())
            .extract()
            .path<String>("clientNumber")

        spec
            .contentType(MediaType.APPLICATION_JSON)
            .`when`()
            .get("/clients/${clientNumber}")
            .then()
            .statusCode(200)
            .body("clientNumber", equalTo(clientNumber))
            .body("dateOfBirth", equalTo("1997-01-01"))
    }

    @Test
    fun `get client that does not exist`() {
        assertThatThrownBy { clientController.getClientById("-1") }
            .isInstanceOf(BusinessException::class.java)
            .hasMessageContaining("No client exists with client number")
    }

    @Test
    fun `get client with blank client number`() {
        assertThatThrownBy { clientController.getClientById("") }
            .isInstanceOf(ConstraintViolationException::class.java)
            .hasMessageContaining("must not be blank")
    }

    @Test
    fun `get clients`() {
        clientController.createClient(ClientFixture.createClientRequest_jane())
        clientController.createClient(ClientFixture.createClientRequest_john())

        val clients = clientController.getClients()

        assertThat(clients).hasSize(2)
    }
}