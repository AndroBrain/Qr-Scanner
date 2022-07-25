package com.androbrain.qr.scanner.data.phone.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.androbrain.qr.scanner.data.phone.PhoneModel
import java.util.*
import org.threeten.bp.LocalDate

@Entity
data class PhoneEntity(
    @PrimaryKey val uuid: UUID = UUID.randomUUID(),
    val scanDate: LocalDate,
    val display: String?,
    val raw: String?,
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
