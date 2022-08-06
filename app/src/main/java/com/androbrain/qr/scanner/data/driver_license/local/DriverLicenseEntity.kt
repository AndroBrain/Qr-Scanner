package com.androbrain.qr.scanner.data.driver_license.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.androbrain.qr.scanner.data.driver_license.DriverLicenseModel
import java.util.UUID
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDateTime

@Entity
data class DriverLicenseEntity(
    @PrimaryKey val id: Int,
    val scanDate: LocalDateTime,
    val display: String?,
    val raw: String?,
    val addressCity: String?,
    val addressState: String?,
    val addressStreet: String?,
    val addressZip: String?,
    val birthDate: String?,
    val documentType: String?,
    val expirationDate: String?,
    val firstName: String?,
    val gender: String?,
    val issueDate: String?,
    val issuingCountry: String?,
    val lastName: String?,
    val licenseNumber: String?,
    val middleName: String?,
)

fun DriverLicenseModel.toEntity() = DriverLicenseEntity(
    id = hashCode(),
    scanDate = scanDate,
    display = display,
    raw = raw,
    addressCity = addressCity,
    addressState = addressState,
    addressStreet = addressStreet,
    addressZip = addressZip,
    birthDate = birthDate,
    documentType = documentType,
    expirationDate = expirationDate,
    firstName = firstName,
    gender = gender,
    issueDate = issueDate,
    issuingCountry = issuingCountry,
    lastName = lastName,
    licenseNumber = licenseNumber,
    middleName = middleName,
)

fun DriverLicenseEntity.toModel() = DriverLicenseModel(
    scanDate = scanDate,
    display = display,
    raw = raw,
    addressCity = addressCity,
    addressState = addressState,
    addressStreet = addressStreet,
    addressZip = addressZip,
    birthDate = birthDate,
    documentType = documentType,
    expirationDate = expirationDate,
    firstName = firstName,
    gender = gender,
    issueDate = issueDate,
    issuingCountry = issuingCountry,
    lastName = lastName,
    licenseNumber = licenseNumber,
    middleName = middleName,
)
