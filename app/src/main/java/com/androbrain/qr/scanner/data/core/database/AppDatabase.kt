package com.androbrain.qr.scanner.data.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.androbrain.qr.scanner.data.calendar_event.local.CalendarEventDao
import com.androbrain.qr.scanner.data.calendar_event.local.CalendarEventEntity
import com.androbrain.qr.scanner.data.contact_info.local.ContactInfoDao
import com.androbrain.qr.scanner.data.contact_info.local.ContactInfoEntity
import com.androbrain.qr.scanner.data.driver_license.local.DriverLicenseDao
import com.androbrain.qr.scanner.data.driver_license.local.DriverLicenseEntity
import com.androbrain.qr.scanner.data.email.local.EmailDao
import com.androbrain.qr.scanner.data.email.local.EmailEntity
import com.androbrain.qr.scanner.data.geo_point.local.GeoPointDao
import com.androbrain.qr.scanner.data.geo_point.local.GeoPointEntity
import com.androbrain.qr.scanner.data.phone.local.PhoneDao
import com.androbrain.qr.scanner.data.phone.local.PhoneEntity
import com.androbrain.qr.scanner.data.sms.local.SmsDao
import com.androbrain.qr.scanner.data.sms.local.SmsEntity
import com.androbrain.qr.scanner.data.url.local.UrlDao
import com.androbrain.qr.scanner.data.url.local.UrlEntity
import com.androbrain.qr.scanner.data.wifi.local.WifiDao
import com.androbrain.qr.scanner.data.wifi.local.WifiEntity

@Database(
    entities = [
        UrlEntity::class,
        WifiEntity::class,
        SmsEntity::class,
        PhoneEntity::class,
        GeoPointEntity::class,
        EmailEntity::class,
        DriverLicenseEntity::class,
        ContactInfoEntity::class,
        CalendarEventEntity::class,
    ],
    version = 1,
    exportSchema = true,
)
@TypeConverters(TimeAndDateConverters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun urlDao(): UrlDao
    abstract fun wifiDao(): WifiDao
    abstract fun smsDao(): SmsDao
    abstract fun phoneDao(): PhoneDao
    abstract fun getPointDao(): GeoPointDao
    abstract fun getEmailDao(): EmailDao
    abstract fun getDriverLicenseDao(): DriverLicenseDao
    abstract fun getContactInfoDao(): ContactInfoDao
    abstract fun getCalendarEventDao(): CalendarEventDao
}
