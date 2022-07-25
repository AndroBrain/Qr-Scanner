package com.androbrain.qr.scanner.data.geo_point.local

import com.androbrain.qr.scanner.data.geo_point.GeoPointDataSource
import com.androbrain.qr.scanner.data.geo_point.GeoPointModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class GeoPointLocalDataSource @Inject constructor(
    private val geopointDao: GeoPointDao,
) : GeoPointDataSource {
    override suspend fun insert(geopointModel: GeoPointModel) = withContext(Dispatchers.IO) {
        geopointDao.insert(geopointModel.toEntity())
    }

    override fun getAll() = geopointDao.getAll().map { geopointList ->
        geopointList.map { geopoint -> geopoint.toModel() }
    }
}
