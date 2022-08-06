package com.androbrain.qr.scanner.feature.barcodes.email

import android.content.Context
import com.androbrain.qr.scanner.R
import com.androbrain.qr.scanner.data.email.EmailModel
import com.androbrain.qr.scanner.feature.barcodes.LocalDateTimeFormatterUtil.asDateTime
import com.androbrain.qr.scanner.feature.barcodes.util.BarcodesUtil

object EmailMappers {
    fun EmailModel.toBarcodeInfo(context: Context) = listOfNotNull(
        BarcodesUtil.getBarcodeInfo(
            title = R.string.barcodes_scan_date,
            content = scanDate.asDateTime(context),
        ),
        BarcodesUtil.getBarcodeInfo(
            title = R.string.email_address,
            content = address,
        ),
        BarcodesUtil.getBarcodeInfo(
            title = R.string.email_subject,
            content = subject,
        ),
        BarcodesUtil.getBarcodeInfo(
            title = R.string.email_body,
            content = body,
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
