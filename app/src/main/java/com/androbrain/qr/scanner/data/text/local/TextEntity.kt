package com.androbrain.qr.scanner.data.text.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.androbrain.qr.scanner.data.text.TextModel
import java.util.UUID
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDateTime

@Entity
data class TextEntity(
    @PrimaryKey val id: Int,
    val scanDate: LocalDateTime,
    val display: String?,
    val raw: String?,
)

fun TextModel.toEntity() = TextEntity(
    id = hashCode(),
    scanDate = scanDate,
    display = display,
    raw = raw,
)

fun TextEntity.toModel() = TextModel(
    scanDate = scanDate,
    display = display,
    raw = raw,
)
