package com.androbrain.qr.scanner.data.sms.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.androbrain.qr.scanner.data.sms.SmsModel
import java.util.*

@Entity
data class SmsEntity(
    @PrimaryKey val uuid: UUID = UUID.randomUUID(),
    val display: String?,
    val raw: String?,
    val message: String?,
    val phoneNumber: String?,
)

fun SmsModel.toEntity() = SmsEntity(
    display = display,
    raw = raw,
    message = message,
    phoneNumber = phoneNumber,
)

fun SmsEntity.toModel() = SmsModel(
    display = display,
    raw = raw,
    message = message,
    phoneNumber = phoneNumber,
)
