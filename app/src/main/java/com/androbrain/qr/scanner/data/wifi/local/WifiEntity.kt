package com.androbrain.qr.scanner.data.wifi.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.androbrain.qr.scanner.data.wifi.WifiModel
import org.threeten.bp.LocalDateTime

@Entity
data class WifiEntity(
    val scanDate: LocalDateTime,
    val display: String?,
    @PrimaryKey val raw: String,
    val encryptionType: Int,
    val ssid: String?,
    val password: String?,
)

fun WifiModel.toEntity() = WifiEntity(
    scanDate = scanDate,
    display = display,
    raw = raw,
    encryptionType = encryptionType,
    ssid = ssid,
    password = password,
)

fun WifiEntity.toModel() = WifiModel(
    scanDate = scanDate,
    display = display,
    raw = raw,
    encryptionType = encryptionType,
    ssid = ssid,
    password = password,
)
