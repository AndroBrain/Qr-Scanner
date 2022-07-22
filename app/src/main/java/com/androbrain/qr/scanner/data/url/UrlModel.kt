package com.androbrain.qr.scanner.data.url

import org.threeten.bp.LocalDate

data class UrlModel(
    val title: String?,
    val url: String?,
    val creationDate: LocalDate,
)
