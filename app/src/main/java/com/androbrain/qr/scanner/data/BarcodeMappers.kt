package com.androbrain.qr.scanner.data

import com.androbrain.qr.scanner.data.calendar_event.CalendarEventModel
import com.androbrain.qr.scanner.data.contact_info.ContactInfoAddressModel
import com.androbrain.qr.scanner.data.contact_info.ContactInfoEmailModel
import com.androbrain.qr.scanner.data.contact_info.ContactInfoModel
import com.androbrain.qr.scanner.data.contact_info.ContactInfoPhoneModel
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
import org.threeten.bp.LocalDateTime

fun Barcode.UrlBookmark.toModel(barcodeInfo: DefaultBarcodeInfo) = UrlModel(
    title = title,
    url = url,
    scanDate = LocalDateTime.now(),
    raw = barcodeInfo.raw,
    display = barcodeInfo.display,
)

fun Barcode.WiFi.toModel(barcodeInfo: DefaultBarcodeInfo) = WifiModel(
    scanDate = LocalDateTime.now(),
    display = barcodeInfo.display,
    raw = barcodeInfo.raw,
    encryptionType = encryptionType,
    ssid = ssid,
    password = password,
)

fun Barcode.Sms.toModel(barcodeInfo: DefaultBarcodeInfo) = SmsModel(
    scanDate = LocalDateTime.now(),
    display = barcodeInfo.display,
    raw = barcodeInfo.raw,
    message = message,
    phoneNumber = phoneNumber,
)

fun Barcode.Phone.toModel(barcodeInfo: DefaultBarcodeInfo) = PhoneModel(
    scanDate = LocalDateTime.now(),
    display = barcodeInfo.display,
    raw = barcodeInfo.raw,
    type = type,
    number = number,
)

fun Barcode.GeoPoint.toModel(barcodeInfo: DefaultBarcodeInfo) = GeoPointModel(
    scanDate = LocalDateTime.now(),
    display = barcodeInfo.display,
    raw = barcodeInfo.raw,
    latitude = lat,
    longitude = lng,
)

fun Barcode.Email.toModel(barcodeInfo: DefaultBarcodeInfo) = EmailModel(
    scanDate = LocalDateTime.now(),
    display = barcodeInfo.display,
    raw = barcodeInfo.raw,
    address = address,
    body = body,
    subject = subject,
)

fun Barcode.DriverLicense.toModel(barcodeInfo: DefaultBarcodeInfo) = DriverLicenseModel(
    scanDate = LocalDateTime.now(),
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
    scanDate = LocalDateTime.now(),
    display = barcodeInfo.display,
    raw = barcodeInfo.raw,
    organization = organization,
    title = title,
    firstName = name?.first,
    formattedName = name?.formattedName,
    lastName = name?.last,
    middleName = name?.middle,
    prefixName = name?.prefix,
    pronunciationName = name?.pronunciation,
    suffixName = name?.suffix,
    addresses = addresses.map { it.toContactInfoModel() },
    emails = emails.map { it.toContactInfoModel() },
    phones = phones.map { it.toContactInfoModel() },
    urls = urls,
)

fun Barcode.Address.toContactInfoModel() = ContactInfoAddressModel(
    type = type, addressLines = addressLines.toList()
)

fun Barcode.Email.toContactInfoModel() = ContactInfoEmailModel(
    address = address,
    body = body,
    subject = subject,
)

fun Barcode.Phone.toContactInfoModel() = ContactInfoPhoneModel(
    type = type,
    number = number,
)

fun Barcode.CalendarEvent.toModel(barcodeInfo: DefaultBarcodeInfo) = CalendarEventModel(
    scanDate = LocalDateTime.now(),
    display = barcodeInfo.display,
    raw = barcodeInfo.raw,
    end = end.toLocalDateTime(),
    isEndUtc = end?.isUtc,
    start = start.toLocalDateTime(),
    isStartUtc = start?.isUtc,
    description = description,
    location = location,
    organizer = organizer,
    status = status,
    summary = summary,
)

fun Barcode.CalendarDateTime?.toLocalDateTime() = if (this != null) {
    LocalDateTime.of(
        year.coerceAtLeast(0),
        month.coerceAtLeast(0),
        day.coerceAtLeast(0),
        hours.coerceAtLeast(0),
        minutes.coerceAtLeast(0),
        seconds.coerceAtLeast(0),
    )
} else {
    null
}

fun Barcode.toTextModel() = TextModel(
    scanDate = LocalDateTime.now(),
    display = displayValue,
    raw = rawValue,
)
