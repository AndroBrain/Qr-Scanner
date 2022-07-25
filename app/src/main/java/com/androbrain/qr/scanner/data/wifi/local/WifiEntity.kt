package com.androbrain.qr.scanner.data.wifi.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.androbrain.qr.scanner.data.wifi.WifiModel
import java.util.*

@Entity
data class WifiEntity(
    @PrimaryKey val uuid: UUID = UUID.randomUUID(),
    val display: String?,
    val raw: String?,
    val encryptionType: Int,
    val ssid: String?,
    val password: String?,
)

fun WifiModel.toEntity() = WifiEntity(
    display = display,
    raw = raw,
    encryptionType = encryptionType,
    ssid = ssid,
    password = password,
)

fun WifiEntity.toModel() = WifiModel(
    display = display,
    raw = raw,
    encryptionType = encryptionType,
    ssid = ssid,
    password = password,
)
