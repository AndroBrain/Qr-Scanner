package com.androbrain.qr.scanner.data

import com.androbrain.qr.scanner.data.url.UrlModel
import com.androbrain.qr.scanner.feature.history.HistoryBarcode
import kotlinx.coroutines.flow.Flow

interface BarcodeRepository {
    fun getHistory(): Flow<List<HistoryBarcode>>

    suspend fun insertUrl(urlModel: UrlModel)
}
