package com.androbrain.qr.scanner.data.contact_info.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.androbrain.qr.scanner.data.contact_info.ContactInfoAddressModel
import com.androbrain.qr.scanner.data.contact_info.ContactInfoEmailModel
import com.androbrain.qr.scanner.data.contact_info.ContactInfoModel
import com.androbrain.qr.scanner.data.contact_info.ContactInfoPhoneModel
import java.util.UUID
import org.threeten.bp.LocalDate

@Entity
data class ContactInfoEntity(
    @PrimaryKey val id: Int,
    val scanDate: LocalDate,
    val display: String?,
    val raw: String?,
    val organization: String?,
    val title: String?,
    val firstName: String?,
    val formattedName: String?,
    val lastName: String?,
    val middleName: String?,
    val prefixName: String?,
    val pronunciationName: String?,
    val suffixName: String?,
    val addresses: List<ContactInfoAddressModel>,
    val emails: List<ContactInfoEmailModel>,
    val phones: List<ContactInfoPhoneModel>,
    val urls: List<String>,
)

fun ContactInfoModel.toEntity() = ContactInfoEntity(
    id = hashCode(),
    scanDate = scanDate,
    display = display,
    raw = raw,
    organization = organization,
    title = title,
    firstName = firstName,
    formattedName = formattedName,
    lastName = lastName,
    middleName = middleName,
    prefixName = prefixName,
    pronunciationName = pronunciationName,
    suffixName = suffixName,
    addresses = addresses,
    emails = emails,
    phones = phones,
    urls = urls,
)

fun ContactInfoEntity.toModel() = ContactInfoModel(
    scanDate = scanDate,
    display = display,
    raw = raw,
    organization = organization,
    title = title,
    firstName = firstName,
    formattedName = formattedName,
    lastName = lastName,
    middleName = middleName,
    prefixName = prefixName,
    pronunciationName = pronunciationName,
    suffixName = suffixName,
    addresses = addresses,
    emails = emails,
    phones = phones,
    urls = urls,
)
