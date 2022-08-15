package com.androbrain.qr.scanner.data.text.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.androbrain.qr.scanner.data.text.TextModel
import org.threeten.bp.LocalDateTime

@Entity
data class TextEntity(
    val scanDate: LocalDateTime,
    val display: String?,
    @PrimaryKey val raw: String,
)

fun TextModel.toEntity() = TextEntity(
    scanDate = scanDate,
    display = display,
    raw = raw,
)

fun TextEntity.toModel() = TextModel(
    scanDate = scanDate,
    display = display,
    raw = raw,
)
