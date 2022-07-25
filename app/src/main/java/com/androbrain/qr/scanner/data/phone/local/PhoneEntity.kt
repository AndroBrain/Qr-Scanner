package com.androbrain.qr.scanner.data.phone.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.androbrain.qr.scanner.data.phone.PhoneModel
import java.util.*

@Entity
data class PhoneEntity(
    @PrimaryKey val uuid: UUID = UUID.randomUUID(),
    val display: String?,
    val raw: String?,
    val type: Int,
    val number: String?,
)

fun PhoneModel.toEntity() = PhoneEntity(
    display = display,
    raw = raw,
    type = type,
    number = number,
)

fun PhoneEntity.toModel() = PhoneModel(
    display = display,
    raw = raw,
    type = type,
    number = number,
)
