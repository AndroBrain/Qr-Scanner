package com.androbrain.qr.scanner.feature.barcodes.url

import android.content.Context
import com.androbrain.qr.scanner.R
import com.androbrain.qr.scanner.data.url.UrlModel
import com.androbrain.qr.scanner.feature.barcodes.LocalDateTimeFormatterUtil.asDateTime
import com.androbrain.qr.scanner.feature.barcodes.util.BarcodesUtil

object UrlMappers {
    fun UrlModel.toBarcodeInfo(context: Context) = listOfNotNull(
        BarcodesUtil.getBarcodeInfo(
            title = R.string.barcodes_scan_date,
            content = scanDate.asDateTime(context),
        ),
        BarcodesUtil.getBarcodeInfo(
            title = R.string.url_title,
            content = title,
        ),
        BarcodesUtil.getBarcodeInfo(
            title = R.string.url_url,
            content = url,
        ),
        BarcodesUtil.getBarcodeInfo(
            title = R.string.barcodes_display,
            content = display,
        ),
        BarcodesUtil.getBarcodeInfo(
            title = R.string.barcodes_raw,
            content = raw,
        ),
    )
}
