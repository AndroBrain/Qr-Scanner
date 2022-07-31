package com.androbrain.qr.scanner.feature.barcodes.text

import com.androbrain.qr.scanner.R
import com.androbrain.qr.scanner.data.text.TextModel
import com.androbrain.qr.scanner.feature.barcodes.model.info.BarcodeInfo
import com.androbrain.qr.scanner.feature.barcodes.util.BarcodesUtil

object TextMappers {
    fun TextModel.toBarcodesInfo() = listOfNotNull(
        BarcodeInfo(
            title = R.string.barcodes_scan_date,
            content = scanDate.toString()
        ),
        BarcodesUtil.getBarcodeInfo(
            title = R.string.barcodes_display,
            content = display
        ),
        BarcodesUtil.getBarcodeInfo(
            title = R.string.barcodes_raw,
            content = raw
        ),
    )
}
