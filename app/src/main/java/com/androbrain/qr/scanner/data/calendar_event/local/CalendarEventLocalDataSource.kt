package com.androbrain.qr.scanner.data.calendar_event.local

import com.androbrain.qr.scanner.data.calendar_event.CalendarEventDataSource
import com.androbrain.qr.scanner.data.calendar_event.CalendarEventModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class CalendarEventLocalDataSource @Inject constructor(
    private val calendarEventDao: CalendarEventDao,
) : CalendarEventDataSource {
    override suspend fun insert(calendareventModel: CalendarEventModel) =
        withContext(Dispatchers.IO) {
            calendarEventDao.insert(calendareventModel.toEntity())
        }

    override fun getAll() = calendarEventDao.getAll().map { calendareventList ->
        calendareventList.map { calendarevent -> calendarevent.toModel() }
    }
}
