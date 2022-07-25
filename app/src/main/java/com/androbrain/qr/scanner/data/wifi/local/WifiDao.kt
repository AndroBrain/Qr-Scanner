package com.androbrain.qr.scanner.data.wifi.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface WifiDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: WifiEntity)

    @Query("SELECT * FROM WifiEntity")
    fun getAll(): Flow<List<WifiEntity>>
}
