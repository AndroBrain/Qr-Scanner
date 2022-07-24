package com.androbrain.qr.scanner.data.url.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.androbrain.qr.scanner.data.url.UrlModel
import org.threeten.bp.LocalDate
import java.util.*

@Entity
data class UrlEntity(
    @PrimaryKey val uuid: UUID = UUID.randomUUID(),
    val title: String?,
    val url: String?,
    val creationDate: LocalDate,
    val raw: String?,
)

fun UrlModel.toEntity() = UrlEntity(
    title = title,
    url = url,
    creationDate = creationDate,
    raw = raw,
)

fun UrlEntity.toModel() = UrlModel(
    title = title,
    url = url,
    creationDate = creationDate,
    raw = raw,
)