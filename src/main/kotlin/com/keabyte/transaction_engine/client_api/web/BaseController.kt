package com.keabyte.transaction_engine.client_api.web

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.uri.UriBuilder
import io.swagger.v3.oas.annotations.Hidden
import java.net.URI

@Controller
internal class BaseController {
    @Get
    @Hidden
    fun <T> home(): HttpResponse<T> {
        return HttpResponse.seeOther(SWAGGER_UI)
    }

    companion object {
        private val SWAGGER_UI: URI = UriBuilder.of("/swagger-ui").path("index.html").build()
    }
}