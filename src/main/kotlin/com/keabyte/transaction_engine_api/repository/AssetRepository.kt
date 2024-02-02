package com.keabyte.transaction_engine_api.repository

import com.keabyte.transaction_engine_api.repository.entity.asset.AssetEntity
import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.CrudRepository
import java.util.*

@Repository
interface AssetRepository : CrudRepository<AssetEntity, Long> {
    fun findByAssetCode(assetCode: String): Optional<AssetEntity>
}