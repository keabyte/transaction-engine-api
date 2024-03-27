package com.keabyte.transaction_engine.client_api.web

import com.keabyte.transaction_engine.client_api.fixture.ClientFixture
import com.keabyte.transaction_engine.client_api.type.AccountType
import com.keabyte.transaction_engine.client_api.web.model.account.CreateAccountRequest
import io.micronaut.http.MediaType
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import io.restassured.specification.RequestSpecification
import org.hamcrest.Matchers
import org.junit.jupiter.api.Test

@MicronautTest
class AccountControllerTest {

    @Test
    fun `create account`(spec: RequestSpecification) {
        val clientNumber = spec
            .contentType(MediaType.APPLICATION_JSON)
            .body(ClientFixture.createClientRequest_john())
            .`when`()
            .post("/clients")
            .then()
            .statusCode(200)
            .body("clientNumber", Matchers.notNullValue())
            .extract()
            .path<String>("clientNumber")

        val accountNumber = spec
            .contentType(MediaType.APPLICATION_JSON)
            .body(CreateAccountRequest(clientNumber = clientNumber, type = AccountType.INVESTMENT))
            .`when`()
            .post("/accounts")
            .then()
            .statusCode(200)
            .body("clientNumber", Matchers.notNullValue())
            .body("accountNumber", Matchers.notNullValue())
            .extract()
            .path<String>("accountNumber")
    }
}