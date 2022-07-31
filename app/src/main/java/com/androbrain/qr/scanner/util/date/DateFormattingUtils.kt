package com.androbrain.qr.scanner.util.date

import java.util.Locale
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter

private const val DAY_MONTH_YEAR_PATTERN = "dd MMM yyyy"

object DateFormattingUtils {
    fun formatToDayMonthYear(date: LocalDate): String =
        date.format(DateTimeFormatter.ofPattern(DAY_MONTH_YEAR_PATTERN, Locale.getDefault()))
}
