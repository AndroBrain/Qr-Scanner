package com.androbrain.qr.scanner.data.sms.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.androbrain.qr.scanner.data.sms.SmsModel
import org.threeten.bp.LocalDateTime

@Entity
data class SmsEntity(
    @PrimaryKey val id: Int,
    val scanDate: LocalDateTime,
    val display: String?,
    val raw: String?,
    val message: String?,
    val phoneNumber: String?,
)

fun SmsModel.toEntity() = SmsEntity(
    id = hashCode(),
    scanDate = scanDate,
    display = display,
    raw = raw,
    message = message,
    phoneNumber = phoneNumber,
)

fun SmsEntity.toModel() = SmsModel(
    scanDate = scanDate,
    display = display,
    raw = raw,
    message = message,
    phoneNumber = phoneNumber,
)
