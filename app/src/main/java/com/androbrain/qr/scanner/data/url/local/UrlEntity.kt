package com.androbrain.qr.scanner.data.url.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.androbrain.qr.scanner.data.url.UrlModel
import org.threeten.bp.LocalDate

@Entity
data class UrlEntity(
    @PrimaryKey val id: Long = 0L,
    val title: String?,
    val url: String?,
    val creationDate: LocalDate,
)

fun UrlModel.toEntity() = UrlEntity(
    title = title,
    url = url,
    creationDate = creationDate,
)

fun UrlEntity.toModel() = UrlModel(
    title = title,
    url = url,
    creationDate = creationDate,
)