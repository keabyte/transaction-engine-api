package com.keabyte.transaction_engine_api.web

import com.keabyte.transaction_engine_api.service.AccountService
import com.keabyte.transaction_engine_api.web.model.account.Account
import com.keabyte.transaction_engine_api.web.model.account.CreateAccountRequest
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.http.annotation.Produces

@Controller("/accounts")
@Produces(MediaType.APPLICATION_JSON)
class AccountController(private val accountService: AccountService) {

    @Post
    fun createAccount(request: CreateAccountRequest): Account {
        return accountService.createAccount(request)
    }
}