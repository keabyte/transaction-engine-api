package com.keabyte.transaction_engine_api.web

import com.keabyte.transaction_engine_api.fixture.ClientFixture
import com.keabyte.transaction_engine_api.repository.enum.BalanceEffectType
import com.keabyte.transaction_engine_api.repository.enum.TransactionType
import com.keabyte.transaction_engine_api.web.model.account.CreateAccountRequest
import com.keabyte.transaction_engine_api.web.model.transaction.CreateDepositRequest
import com.keabyte.transaction_engine_api.web.model.transaction.CreateWithdrawalRequest
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.math.BigDecimal

@MicronautTest
class TransactionControllerTest(
    private val transactionController: TransactionController,
    private val clientController: ClientController,
    private val accountController: AccountController
) {

    @Test
    fun `create deposit`() {
        val client = clientController.createClient(ClientFixture.createClientRequest_jane())
        val account = accountController.createAccount(CreateAccountRequest(clientNumber = client.clientNumber))
        val transaction = transactionController.createDeposit(
            CreateDepositRequest(
                accountNumber = account.accountNumber,
                amount = BigDecimal("100.33"),
                currency = "AUD"
            )
        )

        assertThat(transaction)
            .hasNoNullFieldsOrProperties()

        assertThat(transaction.type).isEqualTo(TransactionType.DEPOSIT)

        assertThat(transaction.accountTransactions)
            .hasSize(1)
        val accountTransaction = transaction.accountTransactions[0]
        assertThat(accountTransaction.accountNumber).isEqualTo(account.accountNumber)

        assertThat(accountTransaction.invesmentTransactions)
            .hasSize(1)
        val investmentTransaction = accountTransaction.invesmentTransactions[0]
        assertThat(investmentTransaction.amount).isEqualTo("100.33")
        assertThat(investmentTransaction.currency).isEqualTo("AUD")
        assertThat(investmentTransaction.balanceEffectType).isEqualTo(BalanceEffectType.CREDIT)
    }

    @Test
    fun `create withdrawal`() {
        val client = clientController.createClient(ClientFixture.createClientRequest_jane())
        val account = accountController.createAccount(CreateAccountRequest(clientNumber = client.clientNumber))
        val transaction = transactionController.createWithdrawal(
            CreateWithdrawalRequest(
                accountNumber = account.accountNumber,
                amount = BigDecimal("10.00"),
                currency = "AUD"
            )
        )

        assertThat(transaction.type).isEqualTo(TransactionType.WITHDRAWAL)

        assertThat(transaction.accountTransactions)
            .hasSize(1)
        val accountTransaction = transaction.accountTransactions[0]
        assertThat(accountTransaction.accountNumber).isEqualTo(account.accountNumber)

        assertThat(accountTransaction.invesmentTransactions)
            .hasSize(1)
        val investmentTransaction = accountTransaction.invesmentTransactions[0]
        assertThat(investmentTransaction.amount).isEqualTo("10.00")
        assertThat(investmentTransaction.currency).isEqualTo("AUD")
        assertThat(investmentTransaction.balanceEffectType).isEqualTo(BalanceEffectType.DEBIT)
    }
}