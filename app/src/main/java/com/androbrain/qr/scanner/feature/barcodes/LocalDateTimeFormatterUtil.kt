package com.androbrain.qr.scanner.feature.barcodes

import android.content.Context
import com.androbrain.qr.scanner.R
import org.threeten.bp.LocalDateTime
import org.threeten.bp.format.DateTimeFormatter

object LocalDateTimeFormatterUtil {
    fun LocalDateTime.asDateTime(context: Context) =
        context.getString(
            R.string.barcodes_scan_date_format,
            toLocalDate().toString(),
            toLocalTime().format(DateTimeFormatter.ofPattern("hh:mm:ss"))
        )
}
