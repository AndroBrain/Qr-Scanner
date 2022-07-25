package com.androbrain.qr.scanner.data.url.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.androbrain.qr.scanner.data.url.UrlModel
import java.util.*
import org.threeten.bp.LocalDate

@Entity
data class UrlEntity(
    @PrimaryKey val uuid: UUID = UUID.randomUUID(),
    val scanDate: LocalDate,
    val display: String?,
    val raw: String?,
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
