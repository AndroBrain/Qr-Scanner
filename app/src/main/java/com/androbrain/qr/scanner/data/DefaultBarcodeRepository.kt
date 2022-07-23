package com.androbrain.qr.scanner.data

import com.androbrain.qr.scanner.data.url.UrlDataSource
import com.androbrain.qr.scanner.data.url.UrlModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DefaultBarcodeRepository @Inject constructor(
    private val urlDataSource: UrlDataSource
) : BarcodeRepository {
    override suspend fun insertUrl(urlModel: UrlModel) {
        urlDataSource.insertUrl(urlModel)
    }

    override fun getUrls() = urlDataSource.getUrls()

}