package com.androbrain.qr.scanner.feature.barcodes.calendar_event

import android.content.Context
import com.androbrain.qr.scanner.R
import com.androbrain.qr.scanner.data.calendar_event.CalendarEventModel
import com.androbrain.qr.scanner.feature.barcodes.LocalDateTimeFormatterUtil.asDateTime
import com.androbrain.qr.scanner.feature.barcodes.util.BarcodesUtil

object CalendarEventMappers {
    fun CalendarEventModel.toBarcodeInfo(context: Context) = listOfNotNull(
        BarcodesUtil.getBarcodeInfo(
            title = R.string.calendar_event_start,
            content = start.toString(),
        ),
        BarcodesUtil.getBarcodeInfo(
            title = R.string.calendar_event_end,
            content = end.toString(),
        ),
        BarcodesUtil.getBarcodeInfo(
            title = R.string.calendar_event_location,
            content = location,
        ),
        BarcodesUtil.getBarcodeInfo(
            title = R.string.calendar_event_summary,
            content = summary,
        ),
        BarcodesUtil.getBarcodeInfo(
            title = R.string.calendar_event_status,
            content = status,
        ),
        BarcodesUtil.getBarcodeInfo(
            title = R.string.calendar_event_organizer,
            content = organizer,
        ),
        BarcodesUtil.getBarcodeInfo(
            title = R.string.calendar_event_description,
            content = description,
        ),
        BarcodesUtil.getBarcodeInfo(
            title = R.string.barcodes_display,
            content = display,
        ),
        BarcodesUtil.getBarcodeInfo(
            title = R.string.barcodes_scan_date,
            content = scanDate.asDateTime(context),
        ),
        BarcodesUtil.getBarcodeInfo(
            title = R.string.barcodes_raw,
            content = raw,
        ),
    )
}
