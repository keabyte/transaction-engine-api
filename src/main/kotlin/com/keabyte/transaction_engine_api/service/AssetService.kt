package com.keabyte.transaction_engine_api.service

import com.keabyte.transaction_engine_api.repository.AssetRepository
import com.keabyte.transaction_engine_api.repository.entity.asset.AssetEntity
import com.keabyte.transaction_engine_api.web.model.asset.CreateAssetRequest
import jakarta.inject.Singleton

@Singleton
class AssetService(private val assetRepository: AssetRepository) {

    fun createAsset(request: CreateAssetRequest): AssetEntity {
        return assetRepository.save(request.toEntity())
    }
}