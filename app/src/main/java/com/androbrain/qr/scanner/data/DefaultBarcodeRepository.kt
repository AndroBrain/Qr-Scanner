package com.androbrain.qr.scanner.data

import com.androbrain.qr.scanner.data.url.UrlDataSource
import com.androbrain.qr.scanner.data.url.UrlModel
import com.androbrain.qr.scanner.feature.history.HistoryBarcode
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class DefaultBarcodeRepository @Inject constructor(
    private val urlDataSource: UrlDataSource
) : BarcodeRepository {
    //    TODO use combine to get data from multipleSources
    override fun getHistory() = urlDataSource.getUrls()

    override suspend fun insertUrl(urlModel: UrlModel) {
        urlDataSource.insertUrl(urlModel)
    }
}
