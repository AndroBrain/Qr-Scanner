package com.androbrain.qr.scanner.data.url.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface UrlDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUrl(urlEntity: UrlEntity)

    @Query("SELECT * FROM UrlEntity")
    fun getUrls(): Flow<List<UrlEntity>>
}
