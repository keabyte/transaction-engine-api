package com.keabyte.transaction_engine_api.service

import com.keabyte.transaction_engine_api.exception.BusinessException
import com.keabyte.transaction_engine_api.repository.AssetRepository
import com.keabyte.transaction_engine_api.repository.entity.asset.AssetEntity
import com.keabyte.transaction_engine_api.web.model.asset.CreateAssetRequest
import jakarta.inject.Singleton
import jakarta.validation.constraints.NotBlank

@Singleton
class AssetService(private val assetRepository: AssetRepository) {

    fun getAssetById(@NotBlank assetCode: String): AssetEntity {
        return assetRepository.findByAssetCode(assetCode)
            .orElseThrow { BusinessException("No asset exists for asset code $assetCode") }
    }

    fun createAsset(request: CreateAssetRequest): AssetEntity {
        return assetRepository.save(request.toEntity())
    }
}