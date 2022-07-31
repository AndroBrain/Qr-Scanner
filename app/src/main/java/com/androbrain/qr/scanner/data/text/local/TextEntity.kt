package com.androbrain.qr.scanner.data.text.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.androbrain.qr.scanner.data.text.TextModel
import java.util.UUID
import org.threeten.bp.LocalDate

@Entity
data class TextEntity(
    @PrimaryKey val uuid: UUID = UUID.randomUUID(),
    val scanDate: LocalDate,
    val display: String?,
    val raw: String?,
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
