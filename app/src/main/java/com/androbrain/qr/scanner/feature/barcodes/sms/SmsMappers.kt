package com.androbrain.qr.scanner.feature.barcodes.sms

import com.androbrain.qr.scanner.R
import com.androbrain.qr.scanner.data.sms.SmsModel
import com.androbrain.qr.scanner.feature.barcodes.util.BarcodesUtil

object SmsMappers {
    fun SmsModel.toBarcodeInfo() = listOfNotNull(
        BarcodesUtil.getBarcodeInfo(
            title = R.string.barcodes_scan_date,
            content = scanDate.toString(),
        ),
        BarcodesUtil.getBarcodeInfo(
            title = R.string.sms_message,
            content = message,
        ),
        BarcodesUtil.getBarcodeInfo(
            title = R.string.sms_phone,
            content = phoneNumber,
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
