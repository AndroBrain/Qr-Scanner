package com.androbrain.qr.scanner.data.calendar_event.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CalendarEventDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: CalendarEventEntity)

    @Query("SELECT * FROM CalendarEventEntity")
    fun getAll(): Flow<List<CalendarEventEntity>>
}
