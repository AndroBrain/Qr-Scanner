package com.androbrain.qr.scanner.feature.barcodes.model.info

import androidx.annotation.StringRes

data class InfoCardInput(
    @StringRes val title: Int,
    val content: String,
)
