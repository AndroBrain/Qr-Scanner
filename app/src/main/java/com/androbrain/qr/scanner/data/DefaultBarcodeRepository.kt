package com.androbrain.qr.scanner.data

import com.androbrain.qr.scanner.data.calendar_event.CalendarEventDataSource
import com.androbrain.qr.scanner.data.contact_info.ContactInfoDataSource
import com.androbrain.qr.scanner.data.core.model.DefaultBarcodeInfo
import com.androbrain.qr.scanner.data.driver_license.DriverLicenseDataSource
import com.androbrain.qr.scanner.data.email.EmailDataSource
import com.androbrain.qr.scanner.data.geo_point.GeoPointDataSource
import com.androbrain.qr.scanner.data.geo_point.GeoPointModel
import com.androbrain.qr.scanner.data.phone.PhoneDataSource
import com.androbrain.qr.scanner.data.phone.PhoneModel
import com.androbrain.qr.scanner.data.sms.SmsDataSource
import com.androbrain.qr.scanner.data.sms.SmsModel
import com.androbrain.qr.scanner.data.url.UrlDataSource
import com.androbrain.qr.scanner.data.url.UrlModel
import com.androbrain.qr.scanner.data.wifi.WifiDataSource
import com.androbrain.qr.scanner.data.wifi.WifiModel
import com.androbrain.qr.scanner.feature.history.HistoryBarcode
import com.google.mlkit.vision.barcode.common.Barcode
import javax.inject.Inject
import kotlinx.coroutines.flow.combine
import org.threeten.bp.LocalDate

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
        ) { barcodesArray ->
            buildList {
                barcodesArray.forEach { barcodes ->
                    addAll(barcodes)
                }
            }
        }

    override suspend fun insertBarcode(barcode: Barcode): HistoryBarcode? {
        val barcodeInfo = DefaultBarcodeInfo(raw = barcode.rawValue, display = barcode.displayValue)
        barcode.url?.let { urlBookmark ->
            return insertUrlBookmark(urlBookmark, barcodeInfo)
        }
        barcode.wifi?.let { wifi ->
            return insertWifi(wifi, barcodeInfo)
        }
        barcode.sms?.let { sms ->
            return insertSms(sms, barcodeInfo)
        }
        barcode.phone?.let { phone ->
            return insertPhone(phone, barcodeInfo)
        }
        barcode.geoPoint?.let { geoPoint ->
            return insertGeoPoint(geoPoint, barcodeInfo)
        }
//        TODO add methods for inserting the rest 4 types
        return null
    }

    private suspend fun insertUrlBookmark(
        bookmark: Barcode.UrlBookmark,
        barcodeInfo: DefaultBarcodeInfo
    ): UrlModel {
        val urlModel = UrlModel(
            title = bookmark.title,
            url = bookmark.url,
            creationDate = LocalDate.now(),
            raw = barcodeInfo.raw,
            display = barcodeInfo.display,
        )
        urlDataSource.insertUrl(urlModel)
        return urlModel
    }

    private suspend fun insertWifi(
        wifi: Barcode.WiFi,
        barcodeInfo: DefaultBarcodeInfo,
    ): WifiModel {
        val wifiModel = WifiModel(
            display = barcodeInfo.display,
            raw = barcodeInfo.raw,
            encryptionType = wifi.encryptionType,
            ssid = wifi.ssid,
            password = wifi.password,
        )
        wifiDataSource.insert(wifiModel)
        return wifiModel
    }

    private suspend fun insertSms(
        sms: Barcode.Sms,
        barcodeInfo: DefaultBarcodeInfo,
    ): SmsModel {
        val smsModel = SmsModel(
            display = barcodeInfo.display,
            raw = barcodeInfo.raw,
            message = sms.message,
            phoneNumber = sms.phoneNumber,
        )
        smsDataSource.insert(smsModel)
        return smsModel
    }

    private suspend fun insertPhone(
        phone: Barcode.Phone,
        barcodeInfo: DefaultBarcodeInfo,
    ): HistoryBarcode {
        val phoneModel = PhoneModel(
            display = barcodeInfo.display,
            raw = barcodeInfo.raw,
            type = phone.type,
            number = phone.number,
        )
        phoneDataSource.insert(phoneModel)
        return phoneModel
    }

    private suspend fun insertGeoPoint(
        geoPoint: Barcode.GeoPoint,
        barcodeInfo: DefaultBarcodeInfo,
    ): GeoPointModel {
        val geoPointModel = GeoPointModel(
            display = barcodeInfo.display,
            raw = barcodeInfo.raw,
            latitude = geoPoint.lat,
            longitude = geoPoint.lng,
        )
        geoPointDataSource.insert(geoPointModel)
        return geoPointModel
    }

}
