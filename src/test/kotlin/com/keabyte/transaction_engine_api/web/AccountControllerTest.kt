package com.keabyte.transaction_engine_api.web

import com.keabyte.transaction_engine_api.fixture.ClientFixture
import com.keabyte.transaction_engine_api.web.model.account.CreateAccountRequest
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

@MicronautTest
class AccountControllerTest(
    private val clientController: ClientController,
    private val accountController: AccountController
) {

    @Test
    fun `create account`() {
        val client = clientController.createClient(ClientFixture.createClientRequest_jane())
        val account = accountController.createAccount(CreateAccountRequest(clientNumber = client.clientNumber))

        Assertions.assertThat(account)
            .hasNoNullFieldsOrProperties()
    }
}