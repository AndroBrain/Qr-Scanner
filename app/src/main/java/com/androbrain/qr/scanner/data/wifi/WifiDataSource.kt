package com.androbrain.qr.scanner.data.wifi

import kotlinx.coroutines.flow.Flow

interface WifiDataSource {
    suspend fun insert(wifiModel: WifiModel)

    fun getAll(): Flow<List<WifiModel>>
}
