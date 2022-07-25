package com.androbrain.qr.scanner.data.geo_point.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.androbrain.qr.scanner.data.geo_point.GeoPointModel
import java.util.*
import org.threeten.bp.LocalDate

@Entity
data class GeoPointEntity(
    @PrimaryKey val uuid: UUID = UUID.randomUUID(),
    val scanDate: LocalDate,
    val display: String?,
    val raw: String?,
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
