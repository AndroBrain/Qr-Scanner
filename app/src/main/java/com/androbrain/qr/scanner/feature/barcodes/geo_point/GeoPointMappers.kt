package com.androbrain.qr.scanner.feature.barcodes.geo_point

import android.content.Context
import com.androbrain.qr.scanner.R
import com.androbrain.qr.scanner.data.geo_point.GeoPointModel
import com.androbrain.qr.scanner.feature.barcodes.LocalDateTimeFormatterUtil.asDateTime
import com.androbrain.qr.scanner.feature.barcodes.util.BarcodesUtil

object GeoPointMappers {
    fun GeoPointModel.toBarcodesInfo(context: Context) = listOfNotNull(
        BarcodesUtil.getBarcodeInfo(
            title = R.string.barcodes_scan_date,
            content = scanDate.asDateTime(context),
        ),
        BarcodesUtil.getBarcodeInfo(
            title = R.string.geo_point_latitude,
            content = latitude.toString(),
        ),
        BarcodesUtil.getBarcodeInfo(
            title = R.string.geo_point_longitude,
            content = longitude.toString(),
        ),
        BarcodesUtil.getBarcodeInfo(
            title = R.string.barcodes_display,
            content = display,
        ),
        BarcodesUtil.getBarcodeInfo(
            title = R.string.barcodes_raw,
            content = raw,
        ),
    )
}
