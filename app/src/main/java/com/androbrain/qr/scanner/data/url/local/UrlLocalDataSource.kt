package com.androbrain.qr.scanner.data.url.local

import com.androbrain.qr.scanner.data.url.UrlDataSource
import com.androbrain.qr.scanner.data.url.UrlModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class UrlLocalDataSource @Inject constructor(
    private val urlDao: UrlDao,
) : UrlDataSource {
    override suspend fun insertUrl(urlModel: UrlModel) = withContext(Dispatchers.IO) {
        urlDao.insertUrl(urlModel.toEntity())
    }

    override fun getUrls() = urlDao.getUrls().map { urlList ->
        urlList.map { url -> url.toModel() }
    }
}
