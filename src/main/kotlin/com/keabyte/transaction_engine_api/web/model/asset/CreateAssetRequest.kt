package com.keabyte.transaction_engine_api.web.model.asset

import com.keabyte.transaction_engine_api.repository.entity.asset.AssetEntity
import io.micronaut.serde.annotation.Serdeable

@Serdeable
data class CreateAssetRequest(val assetCode: String, val name: String, val unitScale: Int) {
    fun toEntity() = AssetEntity(
        assetCode = assetCode,
        name = name, unitScale = unitScale
    )
}