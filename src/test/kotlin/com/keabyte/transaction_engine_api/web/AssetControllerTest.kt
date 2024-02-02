package com.keabyte.transaction_engine_api.web

import com.keabyte.transaction_engine_api.web.model.asset.CreateAssetRequest
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

@MicronautTest
class AssetControllerTest(private val assetController: AssetController) {

    @Test
    fun `create asset`() {
        val request = CreateAssetRequest(
            "SPY",
            "SPDR S&P 500 ETF Trust",
            6
        )
        val asset = assetController.createAsset(request)

        assertThat(asset)
            .usingRecursiveComparison()
            .ignoringFields("assetCode")
            .isEqualTo(request)
    }
}