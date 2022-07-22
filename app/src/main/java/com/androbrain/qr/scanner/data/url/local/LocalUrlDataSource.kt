package com.androbrain.qr.scanner.data.url.local

import com.androbrain.qr.scanner.data.url.UrlDataSource
import com.androbrain.qr.scanner.data.url.UrlModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalUrlDataSource @Inject constructor(
    private val urlDao: UrlDao,
) : UrlDataSource {
    override fun insertUrl(urlModel: UrlModel) {
        urlDao.insertUrl(urlModel.toEntity())
    }

    override fun getUrls() = urlDao.getUrls().map { urlList ->
        urlList.map { url -> url.toModel() }
    }
}