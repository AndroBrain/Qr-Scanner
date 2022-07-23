package com.androbrain.qr.scanner.data.url

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import org.threeten.bp.LocalDate

@Parcelize
data class UrlModel(
    val title: String?,
    val url: String?,
    val creationDate: LocalDate,
    val raw: String?,
) : Parcelable
