package com.androbrain.qr.scanner.feature.barcodes.phone

import android.content.Context
import com.androbrain.qr.scanner.R
import com.androbrain.qr.scanner.data.phone.PhoneModel
import com.androbrain.qr.scanner.feature.barcodes.LocalDateTimeFormatterUtil.asDateTime
import com.androbrain.qr.scanner.feature.barcodes.util.BarcodesUtil
import com.google.mlkit.vision.barcode.common.Barcode

object PhoneMappers {
    fun PhoneModel.toBarcodeInfo(context: Context) = listOfNotNull(
        BarcodesUtil.getBarcodeInfo(
            title = R.string.phone_type,
            content = when (type) {
                Barcode.Phone.TYPE_UNKNOWN -> context.getString(R.string.phone_unknown)
                Barcode.Phone.TYPE_WORK -> context.getString(R.string.phone_work)
                Barcode.Phone.TYPE_HOME -> context.getString(R.string.phone_home)
                Barcode.Phone.TYPE_FAX -> context.getString(R.string.phone_fax)
                Barcode.Phone.TYPE_MOBILE -> context.getString(R.string.phone_mobile)
                else -> null
            }
        ),
        BarcodesUtil.getBarcodeInfo(
            title = R.string.phone_number,
            content = number,
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
