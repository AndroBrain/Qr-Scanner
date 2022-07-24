package com.androbrain.qr.scanner.data.core.model

import com.google.mlkit.vision.barcode.common.Barcode

data class DefaultBarcodeInfo(
    val raw: String?,
    val display: String?
)

fun Barcode.toDefaultInfo() = DefaultBarcodeInfo(
    raw = rawValue,
    display = displayValue,
)
