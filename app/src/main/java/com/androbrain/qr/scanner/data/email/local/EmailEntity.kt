package com.androbrain.qr.scanner.data.email.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.androbrain.qr.scanner.data.email.EmailModel
import org.threeten.bp.LocalDateTime

@Entity
data class EmailEntity(
    val scanDate: LocalDateTime,
    val display: String?,
    @PrimaryKey val raw: String,
    val address: String?,
    val body: String?,
    val subject: String?,
)

fun EmailModel.toEntity() = EmailEntity(
    scanDate = scanDate,
    display = display,
    raw = raw,
    address = address,
    body = body,
    subject = subject,
)

fun EmailEntity.toModel() = EmailModel(
    scanDate = scanDate,
    display = display,
    raw = raw,
    address = address,
    body = body,
    subject = subject,
)
