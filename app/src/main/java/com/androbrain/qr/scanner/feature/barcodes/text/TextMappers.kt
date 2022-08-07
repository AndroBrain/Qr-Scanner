package com.androbrain.qr.scanner.feature.barcodes.text

import android.content.Context
import com.androbrain.qr.scanner.R
import com.androbrain.qr.scanner.data.text.TextModel
import com.androbrain.qr.scanner.feature.barcodes.LocalDateTimeFormatterUtil.asDateTime
import com.androbrain.qr.scanner.feature.barcodes.model.info.BarcodeInfo
import com.androbrain.qr.scanner.feature.barcodes.util.BarcodesUtil

object TextMappers {
    fun TextModel.toBarcodesInfo(context: Context) = listOfNotNull(
        BarcodesUtil.getBarcodeInfo(
            title = R.string.barcodes_display,
            content = display
        ),
        BarcodeInfo(
            title = R.string.barcodes_scan_date,
            content = scanDate.asDateTime(context)
        ),
        BarcodesUtil.getBarcodeInfo(
            title = R.string.barcodes_raw,
            content = raw
        ),
    )
}
