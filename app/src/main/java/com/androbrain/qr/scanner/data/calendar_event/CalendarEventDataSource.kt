package com.androbrain.qr.scanner.data.calendar_event

import kotlinx.coroutines.flow.Flow

interface CalendarEventDataSource {
    suspend fun insert(calendareventModel: CalendarEventModel)

    fun getAll(): Flow<List<CalendarEventModel>>
}
