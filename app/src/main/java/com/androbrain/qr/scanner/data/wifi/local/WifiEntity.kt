package com.androbrain.qr.scanner.data.wifi.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.androbrain.qr.scanner.data.wifi.WifiModel
import java.util.UUID
import org.threeten.bp.LocalDate

@Entity
data class WifiEntity(
    @PrimaryKey val uuid: UUID = UUID.randomUUID(),
    val scanDate: LocalDate,
    val display: String?,
    val raw: String?,
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
