package com.androbrain.qr.scanner.data

import com.androbrain.qr.scanner.data.calendar_event.CalendarEventDataSource
import com.androbrain.qr.scanner.data.contact_info.ContactInfoDataSource
import com.androbrain.qr.scanner.data.core.model.DefaultBarcodeInfo
import com.androbrain.qr.scanner.data.driver_license.DriverLicenseDataSource
import com.androbrain.qr.scanner.data.email.EmailDataSource
import com.androbrain.qr.scanner.data.geo_point.GeoPointDataSource
import com.androbrain.qr.scanner.data.phone.PhoneDataSource
import com.androbrain.qr.scanner.data.sms.SmsDataSource
import com.androbrain.qr.scanner.data.text.local.TextDataSource
import com.androbrain.qr.scanner.data.url.UrlDataSource
import com.androbrain.qr.scanner.data.wifi.WifiDataSource
import com.androbrain.qr.scanner.feature.history.HistoryBarcode
import com.google.mlkit.vision.barcode.common.Barcode
import javax.inject.Inject
import kotlinx.coroutines.flow.combine

class DefaultBarcodeRepository @Inject constructor(
    private val urlDataSource: UrlDataSource,
    private val wifiDataSource: WifiDataSource,
    private val smsDataSource: SmsDataSource,
    private val phoneDataSource: PhoneDataSource,
    private val geoPointDataSource: GeoPointDataSource,
    private val emailDataSource: EmailDataSource,
    private val driverLicenseDataSource: DriverLicenseDataSource,
    private val contactInfoDataSource: ContactInfoDataSource,
    private val calendarEventDataSource: CalendarEventDataSource,
    private val textDataSource: TextDataSource,
) : BarcodeRepository {
    override fun getHistory() =
        combine(
            urlDataSource.getUrls(),
            wifiDataSource.getAll(),
            smsDataSource.getAll(),
            phoneDataSource.getAll(),
            geoPointDataSource.getAll(),
            emailDataSource.getAll(),
            driverLicenseDataSource.getAll(),
            contactInfoDataSource.getAll(),
            calendarEventDataSource.getAll(),
            textDataSource.getAll(),
        ) { barcodesArray ->
            buildList {
                barcodesArray.forEach { barcodes ->
                    addAll(barcodes)
                }
            }.sortedByDescending { it.scanDate }
        }

    override suspend fun insertBarcode(barcode: Barcode): HistoryBarcode {
        val barcodeInfo = DefaultBarcodeInfo(raw = barcode.rawValue, display = barcode.displayValue)
        barcode.url?.let { urlBookmark ->
            return urlBookmark.toModel(barcodeInfo)
                .also { urlModel -> urlDataSource.insertUrl(urlModel) }
        }
        barcode.wifi?.let { wifi ->
            return wifi.toModel(barcodeInfo)
                .also { wifiModel -> wifiDataSource.insert(wifiModel) }
        }
        barcode.sms?.let { sms ->
            return sms.toModel(barcodeInfo)
                .also { smsModel -> smsDataSource.insert(smsModel) }
        }
        barcode.phone?.let { phone ->
            return phone.toModel(barcodeInfo)
                .also { phoneModel -> phoneDataSource.insert(phoneModel) }
        }
        barcode.geoPoint?.let { geoPoint ->
            return geoPoint.toModel(barcodeInfo)
                .also { geoPointModel -> geoPointDataSource.insert(geoPointModel) }
        }
        barcode.email?.let { email ->
            return email.toModel(barcodeInfo)
                .also { emailModel -> emailDataSource.insert(emailModel) }
        }
        barcode.driverLicense?.let { driverLicense ->
            return driverLicense.toModel(barcodeInfo)
                .also { driverLicenseModel -> driverLicenseDataSource.insert(driverLicenseModel) }
        }
        barcode.contactInfo?.let { contactInfo ->
            return contactInfo.toModel(barcodeInfo)
                .also { contactInfoModel ->
                    contactInfoDataSource.insert(contactInfoModel)
                }
        }
        barcode.calendarEvent?.let { calendarEvent ->
            return calendarEvent.toModel(barcodeInfo)
                .also { calendarEventModel -> calendarEventDataSource.insert(calendarEventModel) }
        }
        return barcode.toTextModel()
            .also { textModel -> textDataSource.insert(textModel) }
    }
}
