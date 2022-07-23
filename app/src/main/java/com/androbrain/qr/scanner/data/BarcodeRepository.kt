package com.androbrain.qr.scanner.data

import com.androbrain.qr.scanner.data.url.UrlModel
import kotlinx.coroutines.flow.Flow

interface BarcodeRepository {
    suspend fun insertUrl(urlModel: UrlModel)

    fun getUrls(): Flow<List<UrlModel>>
}
