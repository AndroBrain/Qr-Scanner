package com.androbrain.qr.scanner.data.phone.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface PhoneDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: PhoneEntity)

    @Query("SELECT * FROM PhoneEntity")
    fun getAll(): Flow<List<PhoneEntity>>
}
