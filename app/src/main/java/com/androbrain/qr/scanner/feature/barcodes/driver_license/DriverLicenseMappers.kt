package com.androbrain.qr.scanner.feature.barcodes.driver_license

import android.content.Context
import com.androbrain.qr.scanner.R
import com.androbrain.qr.scanner.data.driver_license.DriverLicenseModel
import com.androbrain.qr.scanner.feature.barcodes.LocalDateTimeFormatterUtil.asDateTime
import com.androbrain.qr.scanner.feature.barcodes.util.BarcodesUtil

object DriverLicenseMappers {
    fun DriverLicenseModel.toBarcodeInfo(context: Context) = listOfNotNull(
        BarcodesUtil.getBarcodeInfo(
            title = R.string.driver_license_license_number,
            content = licenseNumber,
        ),
        BarcodesUtil.getBarcodeInfo(
            title = R.string.driver_license_first_name,
            content = firstName,
        ),
        BarcodesUtil.getBarcodeInfo(
            title = R.string.driver_license_middle_name,
            content = middleName,
        ),
        BarcodesUtil.getBarcodeInfo(
            title = R.string.driver_license_last_name,
            content = lastName,
        ),
        BarcodesUtil.getBarcodeInfo(
            title = R.string.driver_license_gender,
            content = gender,
        ),
        BarcodesUtil.getBarcodeInfo(
            title = R.string.driver_license_birth_date,
            content = birthDate,
        ),
        BarcodesUtil.getBarcodeInfo(
            title = R.string.driver_license_document_type,
            content = documentType,
        ),
        BarcodesUtil.getBarcodeInfo(
            title = R.string.driver_license_expiration_date,
            content = expirationDate,
        ),
        BarcodesUtil.getBarcodeInfo(
            title = R.string.driver_license_issue_date,
            content = issueDate,
        ),
        BarcodesUtil.getBarcodeInfo(
            title = R.string.driver_license_issuing_country,
            content = issuingCountry,
        ),
        BarcodesUtil.getBarcodeInfo(
            title = R.string.driver_license_address_city,
            content = addressCity,
        ),
        BarcodesUtil.getBarcodeInfo(
            title = R.string.driver_license_address_state,
            content = addressState,
        ),
        BarcodesUtil.getBarcodeInfo(
            title = R.string.driver_license_address_street,
            content = addressStreet,
        ),
        BarcodesUtil.getBarcodeInfo(
            title = R.string.driver_license_address_zip,
            content = addressZip,
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
