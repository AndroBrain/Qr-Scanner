package com.androbrain.qr.scanner.data.core.database

import androidx.room.TypeConverter
import com.androbrain.qr.scanner.data.contact_info.ContactInfoAddressModel
import com.androbrain.qr.scanner.data.contact_info.ContactInfoEmailModel
import com.androbrain.qr.scanner.data.contact_info.ContactInfoPhoneModel
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object JsonConverters {
    @TypeConverter
    @JvmStatic
    fun toAddressList(value: String): List<ContactInfoAddressModel> = Json.decodeFromString(value)

    @TypeConverter
    @JvmStatic
    fun fromAddressList(value: List<ContactInfoAddressModel>) = Json.encodeToString(value)

    @TypeConverter
    @JvmStatic
    fun toEmailList(value: String): List<ContactInfoEmailModel> = Json.decodeFromString(value)

    @TypeConverter
    @JvmStatic
    fun fromEmailList(value: List<ContactInfoEmailModel>) = Json.encodeToString(value)

    @TypeConverter
    @JvmStatic
    fun toPhoneList(value: String): List<ContactInfoPhoneModel> = Json.decodeFromString(value)

    @TypeConverter
    @JvmStatic
    fun fromPhoneList(value: List<ContactInfoPhoneModel>): String = Json.encodeToString(value)

    @TypeConverter
    @JvmStatic
    fun toStringList(value: String): List<String> = Json.decodeFromString(value)

    @TypeConverter
    @JvmStatic
    fun fromStringList(value: List<String>): String = Json.encodeToString(value)

}
