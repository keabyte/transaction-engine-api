package com.keabyte.transaction_engine.client_api.fixture

import com.keabyte.transaction_engine.client_api.web.model.client.CreateClientRequest
import java.time.LocalDate

class ClientFixture {
    companion object {
        fun createClientRequest_john(): CreateClientRequest {
            return CreateClientRequest(firstName = "John", lastName = "Smith", LocalDate.of(1997, 1, 1))
        }

        fun createClientRequest_jane(): CreateClientRequest {
            return CreateClientRequest(firstName = "Jane", lastName = "Doe", LocalDate.of(2000, 12, 31))
        }
    }
}