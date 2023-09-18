package com.keabyte.transactionengine.web

import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.BlockingHttpClient
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.http.client.exceptions.HttpClientResponseException
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import jakarta.inject.Inject
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.util.*

@MicronautTest
class ClientControllerTest {

    @Inject
    @field:Client("/")
    private lateinit var httpClient: HttpClient

    private var blockingClient: BlockingHttpClient? = null

    @BeforeEach
    fun setup() {
        blockingClient = httpClient.toBlocking()
    }

    @Test
    fun `should return bad request when client not found`() {
        val thrown = Assertions.assertThrows(HttpClientResponseException::class.java) {
            blockingClient!!.exchange<Any, Any>(HttpRequest.GET("/clients/0"))
        }
        Assertions.assertNotNull(thrown.response)
        Assertions.assertEquals(HttpStatus.NOT_FOUND, thrown.status)
    }
}