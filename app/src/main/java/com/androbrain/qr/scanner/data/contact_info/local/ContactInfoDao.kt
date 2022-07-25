package com.androbrain.qr.scanner.data.contact_info.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ContactInfoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: ContactInfoEntity)

    @Query("SELECT * FROM ContactInfoEntity")
    fun getAll(): Flow<List<ContactInfoEntity>>
}
