package com.androbrain.qr.scanner.feature.barcodes.util

import androidx.annotation.StringRes
import com.androbrain.qr.scanner.feature.barcodes.model.info.BarcodeInfo

object BarcodesUtil {
    fun getBarcodeCardInputOrNull(
        @StringRes title: Int,
        content: String?,
    ) = if (content.isNullOrBlank()) {
        null
    } else {
        BarcodeInfo(
            title = title,
            content = content
        )
    }
}
