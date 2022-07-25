package com.androbrain.qr.scanner.data.geo_point.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface GeoPointDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: GeoPointEntity)

    @Query("SELECT * FROM GeoPointEntity")
    fun getAll(): Flow<List<GeoPointEntity>>
}
