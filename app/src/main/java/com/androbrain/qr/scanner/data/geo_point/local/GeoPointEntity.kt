package com.androbrain.qr.scanner.data.geo_point.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.androbrain.qr.scanner.data.geo_point.GeoPointModel
import org.threeten.bp.LocalDateTime

@Entity
data class GeoPointEntity(
    val scanDate: LocalDateTime,
    val display: String?,
    @PrimaryKey val raw: String,
    val latitude: Double,
    val longitude: Double,
)

fun GeoPointModel.toEntity() = GeoPointEntity(
    scanDate = scanDate,
    display = display,
    raw = raw,
    latitude = latitude,
    longitude = longitude,
)

fun GeoPointEntity.toModel() = GeoPointModel(
    scanDate = scanDate,
    display = display,
    raw = raw,
    latitude = latitude,
    longitude = longitude,
)
