package com.androbrain.qr.scanner.data.sms.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface SmsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: SmsEntity)

    @Query("SELECT * FROM SmsEntity")
    fun getAll(): Flow<List<SmsEntity>>
}
