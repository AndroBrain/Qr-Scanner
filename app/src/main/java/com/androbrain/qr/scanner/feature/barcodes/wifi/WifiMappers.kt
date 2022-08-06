package com.androbrain.qr.scanner.feature.barcodes.wifi

import android.content.Context
import com.androbrain.qr.scanner.R
import com.androbrain.qr.scanner.data.wifi.WifiModel
import com.androbrain.qr.scanner.feature.barcodes.LocalDateTimeFormatterUtil.asDateTime
import com.androbrain.qr.scanner.feature.barcodes.util.BarcodesUtil
import com.google.mlkit.vision.barcode.common.Barcode

object WifiMappers {
    fun WifiModel.toBarcodeInfo(context: Context) = listOfNotNull(
        BarcodesUtil.getBarcodeInfo(
            title = R.string.barcodes_scan_date,
            content = scanDate.asDateTime(context),
        ),
        BarcodesUtil.getBarcodeInfo(
            title = R.string.wifi_encryption_type,
            content = when (encryptionType) {
                Barcode.WiFi.TYPE_OPEN -> context.getString(R.string.wifi_ssid_open)
                Barcode.WiFi.TYPE_WEP -> context.getString(R.string.wifi_ssid_wep)
                Barcode.WiFi.TYPE_WPA -> context.getString(R.string.wifi_ssid_wpa)
                else -> null
            }
        ),
        BarcodesUtil.getBarcodeInfo(
            title = R.string.wifi_ssid,
            content = ssid
        ),
        BarcodesUtil.getBarcodeInfo(
            title = R.string.wifi_password,
            content = password
        ),
        BarcodesUtil.getBarcodeInfo(
            title = R.string.barcodes_display,
            content = display
        ),
        BarcodesUtil.getBarcodeInfo(
            title = R.string.barcodes_raw,
            content = raw
        ),
    )
}
