package com.androbrain.qr.scanner.data

import com.androbrain.qr.scanner.data.calendar_event.CalendarEventModel
import com.androbrain.qr.scanner.data.contact_info.ContactInfoModel
import com.androbrain.qr.scanner.data.core.model.DefaultBarcodeInfo
import com.androbrain.qr.scanner.data.driver_license.DriverLicenseModel
import com.androbrain.qr.scanner.data.email.EmailModel
import com.androbrain.qr.scanner.data.geo_point.GeoPointModel
import com.androbrain.qr.scanner.data.phone.PhoneModel
import com.androbrain.qr.scanner.data.sms.SmsModel
import com.androbrain.qr.scanner.data.text.TextModel
import com.androbrain.qr.scanner.data.url.UrlModel
import com.androbrain.qr.scanner.data.wifi.WifiModel
import com.google.mlkit.vision.barcode.common.Barcode
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDateTime

fun Barcode.UrlBookmark.toModel(barcodeInfo: DefaultBarcodeInfo) = UrlModel(
    title = title,
    url = url,
    scanDate = LocalDate.now(),
    raw = barcodeInfo.raw,
    display = barcodeInfo.display,
)

fun Barcode.WiFi.toModel(barcodeInfo: DefaultBarcodeInfo) = WifiModel(
    scanDate = LocalDate.now(),
    display = barcodeInfo.display,
    raw = barcodeInfo.raw,
    encryptionType = encryptionType,
    ssid = ssid,
    password = password,
)

fun Barcode.Sms.toModel(barcodeInfo: DefaultBarcodeInfo) = SmsModel(
    scanDate = LocalDate.now(),
    display = barcodeInfo.display,
    raw = barcodeInfo.raw,
    message = message,
    phoneNumber = phoneNumber,
)

fun Barcode.Phone.toModel(barcodeInfo: DefaultBarcodeInfo) = PhoneModel(
    scanDate = LocalDate.now(),
    display = barcodeInfo.display,
    raw = barcodeInfo.raw,
    type = type,
    number = number,
)

fun Barcode.GeoPoint.toModel(barcodeInfo: DefaultBarcodeInfo) = GeoPointModel(
    scanDate = LocalDate.now(),
    display = barcodeInfo.display,
    raw = barcodeInfo.raw,
    latitude = lat,
    longitude = lng,
)

fun Barcode.Email.toModel(barcodeInfo: DefaultBarcodeInfo) = EmailModel(
    scanDate = LocalDate.now(),
    display = barcodeInfo.display,
    raw = barcodeInfo.raw,
    address = address,
    body = body,
    subject = subject,
)

fun Barcode.DriverLicense.toModel(barcodeInfo: DefaultBarcodeInfo) = DriverLicenseModel(
    scanDate = LocalDate.now(),
    display = barcodeInfo.display,
    raw = barcodeInfo.raw,
    addressCity = addressCity,
    addressState = addressState,
    addressStreet = addressStreet,
    addressZip = addressZip,
    birthDate = birthDate,
    documentType = documentType,
    expirationDate = expiryDate,
    firstName = firstName,
    gender = gender,
    issueDate = issueDate,
    issuingCountry = issuingCountry,
    lastName = lastName,
    licenseNumber = licenseNumber,
    middleName = middleName,
)

fun Barcode.ContactInfo.toModel(barcodeInfo: DefaultBarcodeInfo) = ContactInfoModel(
    scanDate = LocalDate.now(),
    display = barcodeInfo.display,
    raw = barcodeInfo.raw,
    organization = organization,
    title = title,
)

fun Barcode.CalendarEvent.toModel(barcodeInfo: DefaultBarcodeInfo) = CalendarEventModel(
    scanDate = LocalDate.now(),
    display = barcodeInfo.display,
    raw = barcodeInfo.raw,
    end = end.toLocalDateTime(),
    start = start.toLocalDateTime(),
    description = description,
    location = location,
    organizer = organizer,
    status = status,
    summary = summary,
)

fun Barcode.CalendarDateTime?.toLocalDateTime() = if (this != null) {
    LocalDateTime.of(
        year,
        month,
        day,
        hours,
        minutes,
        seconds,
    )
} else {
    null
}

fun Barcode.toTextModel() = TextModel(
    scanDate = LocalDate.now(),
    display = displayValue,
    raw = rawValue,
)
