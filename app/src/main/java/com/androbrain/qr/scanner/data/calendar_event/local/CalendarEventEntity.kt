package com.androbrain.qr.scanner.data.calendar_event.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.androbrain.qr.scanner.data.calendar_event.CalendarEventModel
import java.util.*
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDateTime

@Entity
data class CalendarEventEntity(
    @PrimaryKey val uuid: UUID = UUID.randomUUID(),
    val scanDate: LocalDate,
    val display: String?,
    val raw: String?,
    val end: LocalDateTime?,
    val start: LocalDateTime?,
    val description: String?,
    val location: String?,
    val organizer: String?,
    val status: String?,
    val summary: String?,
)

fun CalendarEventModel.toEntity() = CalendarEventEntity(
    scanDate = scanDate,
    display = display,
    raw = raw,
    end = end,
    start = start,
    description = description,
    location = location,
    organizer = organizer,
    status = status,
    summary = summary,
)

fun CalendarEventEntity.toModel() = CalendarEventModel(
    scanDate = scanDate,
    display = display,
    raw = raw,
    end = end,
    start = start,
    description = description,
    location = location,
    organizer = organizer,
    status = status,
    summary = summary,
)
