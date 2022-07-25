package com.androbrain.qr.scanner.data.geo_point

import kotlinx.coroutines.flow.Flow

interface GeoPointDataSource {
    suspend fun insert(geopointModel: GeoPointModel)

    fun getAll(): Flow<List<GeoPointModel>>
}
