package com.keabyte.transaction_engine.client_api.service

import com.keabyte.transaction_engine.client_api.exception.BusinessException
import com.keabyte.transaction_engine.client_api.fixture.ClientFixture
import com.keabyte.transaction_engine.client_api.type.AccountType
import com.keabyte.transaction_engine.client_api.web.AccountController
import com.keabyte.transaction_engine.client_api.web.ClientController
import com.keabyte.transaction_engine.client_api.web.model.account.CreateAccountRequest
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import jakarta.validation.ConstraintViolationException
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

@MicronautTest
class AccountServiceTest(
    private val clientController: ClientController,
    private val accountController: AccountController
) {

    @Test
    fun `create account`() {
        val client = clientController.createClient(ClientFixture.createClientRequest_jane())
        val account = accountController.createAccount(
            CreateAccountRequest(
                clientNumber = client.clientNumber,
                type = AccountType.INVESTMENT
            )
        )

        Assertions.assertThat(account)
            .hasNoNullFieldsOrProperties()
    }

    @Test
    fun `get account`() {
        val client = clientController.createClient(ClientFixture.createClientRequest_john())
        val account1 = accountController.createAccount(
            CreateAccountRequest(
                clientNumber = client.clientNumber,
                type = AccountType.INVESTMENT
            )
        )
        val account2 = accountController.getAccountById(account1.accountNumber)

        Assertions.assertThat(account2)
            .usingRecursiveComparison()
            .isEqualTo(account1)
    }

    @Test
    fun `get account that does not exist`() {
        Assertions.assertThatThrownBy {
            accountController.getAccountById("-1")
        }
            .isInstanceOf(BusinessException::class.java)
            .hasMessageContaining("No account exists")
    }

    @Test
    fun `get account with blank account number`() {
        Assertions.assertThatThrownBy {
            accountController.getAccountById("")
        }
            .isInstanceOf(ConstraintViolationException::class.java)
            .hasMessageContaining("must not be blank")
    }
}
