package com.androbrain.qr.scanner.data.driver_license.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface DriverLicenseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: DriverLicenseEntity)

    @Query("SELECT * FROM DriverLicenseEntity")
    fun getAll(): Flow<List<DriverLicenseEntity>>
}
