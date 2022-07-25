package com.androbrain.qr.scanner.data

import com.androbrain.qr.scanner.feature.history.HistoryBarcode
import com.google.mlkit.vision.barcode.common.Barcode
import kotlinx.coroutines.flow.Flow

interface BarcodeRepository {
    fun getHistory(): Flow<List<HistoryBarcode>>

    suspend fun insertBarcode(barcode: Barcode): HistoryBarcode?
}
