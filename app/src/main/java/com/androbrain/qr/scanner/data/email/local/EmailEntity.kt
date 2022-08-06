package com.androbrain.qr.scanner.data.email.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.androbrain.qr.scanner.data.email.EmailModel
import java.util.UUID
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDateTime

@Entity
data class EmailEntity(
    @PrimaryKey val id: Int,
    val scanDate: LocalDateTime,
    val display: String?,
    val raw: String?,
    val address: String?,
    val body: String?,
    val subject: String?,
)

fun EmailModel.toEntity() = EmailEntity(
    id = hashCode(),
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
