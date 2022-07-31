package com.androbrain.qr.scanner.feature.barcodes.contact_info

import com.androbrain.qr.scanner.feature.barcodes.model.info.BarcodeInfo

data class BarcodeHeaderWithInfo(
    val header: CharSequence,
    val info: List<BarcodeInfo>
)
