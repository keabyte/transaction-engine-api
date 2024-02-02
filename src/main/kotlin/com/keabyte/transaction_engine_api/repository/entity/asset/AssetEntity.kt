package com.keabyte.transaction_engine_api.repository.entity.asset

import com.keabyte.transaction_engine_api.web.model.asset.Asset
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity(name = "asset")
data class AssetEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long? = null,
    val assetCode: String,
    val name: String,
    val unitScale: Int
) {
    fun toModel() = Asset(
        assetCode = assetCode,
        name = name,
        unitScale = unitScale
    )
}