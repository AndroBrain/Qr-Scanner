package com.androbrain.qr.scanner.data.phone.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.androbrain.qr.scanner.data.phone.PhoneModel
import org.threeten.bp.LocalDateTime

@Entity
data class PhoneEntity(
    val scanDate: LocalDateTime,
    val display: String?,
    @PrimaryKey val raw: String,
    val type: Int,
    val number: String?,
)

fun PhoneModel.toEntity() = PhoneEntity(
    scanDate = scanDate,
    display = display,
    raw = raw,
    type = type,
    number = number,
)

fun PhoneEntity.toModel() = PhoneModel(
    scanDate = scanDate,
    display = display,
    raw = raw,
    type = type,
    number = number,
)
