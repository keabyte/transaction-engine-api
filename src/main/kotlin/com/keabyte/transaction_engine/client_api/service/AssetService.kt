package com.keabyte.transaction_engine.client_api.service

import com.keabyte.transaction_engine.client_api.exception.BusinessException
import com.keabyte.transaction_engine.client_api.repository.AssetRepository
import com.keabyte.transaction_engine.client_api.repository.entity.asset.AssetEntity
import com.keabyte.transaction_engine.client_api.web.model.asset.CreateAssetRequest
import jakarta.inject.Singleton
import jakarta.validation.constraints.NotBlank

@Singleton
open class AssetService(private val assetRepository: com.keabyte.transaction_engine.client_api.repository.AssetRepository) {

    open fun getAssetById(@NotBlank assetCode: String): com.keabyte.transaction_engine.client_api.repository.entity.asset.AssetEntity {
        return assetRepository.findByAssetCode(assetCode)
            .orElseThrow { com.keabyte.transaction_engine.client_api.exception.BusinessException("No asset exists for asset code $assetCode") }
    }

    fun createAsset(request: CreateAssetRequest): com.keabyte.transaction_engine.client_api.repository.entity.asset.AssetEntity {
        return assetRepository.save(request.toEntity())
    }
}