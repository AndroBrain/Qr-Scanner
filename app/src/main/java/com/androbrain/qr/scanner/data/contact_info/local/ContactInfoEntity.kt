package com.androbrain.qr.scanner.data.contact_info.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.androbrain.qr.scanner.data.contact_info.ContactInfoModel
import java.util.*

@Entity
data class ContactInfoEntity(
    @PrimaryKey val uuid: UUID = UUID.randomUUID(),
    val display: String?,
    val raw: String?,
//    TODO val personName: PersonNameModel
    val organization: String?,
    val title: String?,
//    TODO figure out converters or relationships
//    adresses: List<> TODO add adress model
//    val emails: List<EmailEntity>,
//    val phones: List<PhoneEntity>,
//    val urls: List<UrlEntity>,
)

fun ContactInfoModel.toEntity() = ContactInfoEntity(
    display = display,
    raw = raw,
    organization = organization,
    title = title,
//    emails = emails.map { it.toEntity() },
//    phones = phones.map { it.toEntity() },
//    urls = urls.map { it.toEntity() },
)

fun ContactInfoEntity.toModel() = ContactInfoModel(
    display = display,
    raw = raw,
    organization = organization,
    title = title,
//    emails = emails.map { it.toModel() },
//    phones = phones.map { it.toModel() },
//    urls = urls.map { it.toModel() },
)
