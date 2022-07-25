package com.androbrain.qr.scanner.data.wifi.local

import com.androbrain.qr.scanner.data.wifi.WifiDataSource
import com.androbrain.qr.scanner.data.wifi.WifiModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class WifiLocalDataSource @Inject constructor(
    private val wifiDao: WifiDao,
) : WifiDataSource {
    override suspend fun insert(wifiModel: WifiModel) = withContext(Dispatchers.IO) {
        wifiDao.insert(wifiModel.toEntity())
    }

    override fun getAll() = wifiDao.getAll().map { wifiList ->
        wifiList.map { wifi -> wifi.toModel() }
    }
}
