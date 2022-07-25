package com.androbrain.qr.scanner.data.email.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface EmailDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: EmailEntity)

    @Query("SELECT * FROM EmailEntity")
    fun getAll(): Flow<List<EmailEntity>>
}
