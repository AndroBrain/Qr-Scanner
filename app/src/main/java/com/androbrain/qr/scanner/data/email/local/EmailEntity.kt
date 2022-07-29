package com.androbrain.qr.scanner.data.email.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.androbrain.qr.scanner.data.email.EmailModel
import java.util.*
import org.threeten.bp.LocalDate

@Entity
data class EmailEntity(
    @PrimaryKey val uuid: UUID = UUID.randomUUID(),
    val scanDate: LocalDate,
    val display: String?,
    val raw: String?,
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