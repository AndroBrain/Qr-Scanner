package com.androbrain.qr.scanner.data.url.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.androbrain.qr.scanner.data.url.UrlModel
import org.threeten.bp.LocalDateTime

@Entity
data class UrlEntity(
    @PrimaryKey val id: Int,
    val scanDate: LocalDateTime,
    val display: String?,
    val raw: String?,
    val title: String?,
    val url: String?,
)

fun UrlModel.toEntity() = UrlEntity(
    id = hashCode(),
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
