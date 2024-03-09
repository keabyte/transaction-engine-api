package com.keabyte.transaction_engine.client_api.web

import com.keabyte.transaction_engine.client_api.exception.BusinessException
import com.keabyte.transaction_engine.client_api.fixture.ClientFixture
import com.keabyte.transaction_engine.client_api.web.model.account.CreateAccountRequest
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import jakarta.validation.ConstraintViolationException
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
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

        assertThat(account)
            .hasNoNullFieldsOrProperties()
    }

    @Test
    fun `get account`() {
        val client = clientController.createClient(ClientFixture.createClientRequest_john())
        val account1 = accountController.createAccount(CreateAccountRequest(clientNumber = client.clientNumber))
        val account2 = accountController.getAccountById(account1.accountNumber)

        assertThat(account2)
            .usingRecursiveComparison()
            .isEqualTo(account1)
    }

    @Test
    fun `get account that does not exist`() {
        assertThatThrownBy {
            accountController.getAccountById("-1")
        }
            .isInstanceOf(BusinessException::class.java)
            .hasMessageContaining("No account exists")
    }

    @Test
    fun `get account with blank account number`() {
        assertThatThrownBy {
            accountController.getAccountById("")
        }
            .isInstanceOf(ConstraintViolationException::class.java)
            .hasMessageContaining("must not be blank")
    }
}