package com.androbrain.qr.scanner.data.core.database

import androidx.room.TypeConverter
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDateTime
import org.threeten.bp.format.DateTimeFormatter

object TimeAndDateConverters {
    private val localDateFormatter = DateTimeFormatter.ISO_LOCAL_DATE
    private val localDateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME

    @TypeConverter
    @JvmStatic
    fun toLocalDate(value: String?): LocalDate? = value?.let {
        return localDateFormatter.parse(value, LocalDate::from)
    }

    @TypeConverter
    @JvmStatic
    fun fromLocalDate(date: LocalDate?): String? =
        date?.format(localDateFormatter)

    @TypeConverter
    @JvmStatic
    fun toLocalDateTime(value: String?): LocalDateTime? = value?.let {
        return localDateTimeFormatter.parse(value, LocalDateTime::from)
    }

    @TypeConverter
    @JvmStatic
    fun fromLocalDateTime(date: LocalDateTime?): String? =
        date?.format(localDateTimeFormatter)
}
