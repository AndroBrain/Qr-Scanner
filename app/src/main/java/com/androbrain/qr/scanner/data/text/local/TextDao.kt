package com.androbrain.qr.scanner.data.text.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TextDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: TextEntity)

    @Query("SELECT * FROM TextEntity")
    fun getAll(): Flow<List<TextEntity>>
}
