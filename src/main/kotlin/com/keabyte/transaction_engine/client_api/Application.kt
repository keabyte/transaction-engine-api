package com.keabyte.transaction_engine.client_api

import io.micronaut.runtime.Micronaut.run
import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Info

@OpenAPIDefinition(
    info = Info(
        title = "txn-client-api",
        version = "0.0.1"
    )
)
object Api

fun main(args: Array<String>) {
    run(*args)
}

