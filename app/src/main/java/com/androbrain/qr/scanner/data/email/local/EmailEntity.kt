package com.androbrain.qr.scanner.data.email.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.androbrain.qr.scanner.data.email.EmailModel
import java.util.*

@Entity
data class EmailEntity(
    @PrimaryKey val uuid: UUID = UUID.randomUUID(),
    val display: String?,
    val raw: String?,
    val address: String?,
    val body: String?,
    val subject: String?,
)

fun EmailModel.toEntity() = EmailEntity(
    display = display,
    raw = raw,
    address = address,
    body = body,
    subject = subject,
)

fun EmailEntity.toModel() = EmailModel(
    display = display,
    raw = raw,
    address = address,
    body = body,
    subject = subject,
)
