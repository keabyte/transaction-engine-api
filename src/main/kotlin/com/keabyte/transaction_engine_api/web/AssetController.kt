package com.keabyte.transaction_engine_api.web

import com.keabyte.transaction_engine_api.service.AssetService
import com.keabyte.transaction_engine_api.web.model.asset.Asset
import com.keabyte.transaction_engine_api.web.model.asset.CreateAssetRequest
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post

@Controller
class AssetController(private val assetService: AssetService) {

    @Post
    fun createAsset(request: CreateAssetRequest): Asset {
        return assetService.createAsset(request).toModel()
    }
}