package com.androbrain.qr.scanner.data.url.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.androbrain.qr.scanner.data.url.UrlModel
import org.threeten.bp.LocalDateTime

@Entity
data class UrlEntity(
    val scanDate: LocalDateTime,
    val display: String?,
    @PrimaryKey val raw: String,
    val title: String?,
    val url: String?,
)

fun UrlModel.toEntity() = UrlEntity(
    title = title,
    url = url,
    scanDate = scanDate,
    raw = raw,
    display = display,
)

fun UrlEntity.toModel() = UrlModel(
    title = title,
    url = url,
    scanDate = scanDate,
    raw = raw,
    display = display,
)
