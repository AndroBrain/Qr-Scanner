package com.androbrain.qr.scanner.data.url

import kotlinx.coroutines.flow.Flow

interface UrlDataSource {
    suspend fun insertUrl(urlModel: UrlModel)

    fun getUrls(): Flow<List<UrlModel>>
}
