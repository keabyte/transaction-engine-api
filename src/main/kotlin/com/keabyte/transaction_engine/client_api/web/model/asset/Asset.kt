package com.keabyte.transaction_engine.client_api.web.model.asset

import io.micronaut.serde.annotation.Serdeable

@Serdeable
data class Asset(val assetCode: String, val name: String, val unitScale: Int)
