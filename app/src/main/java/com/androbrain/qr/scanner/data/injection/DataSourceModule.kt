package com.androbrain.qr.scanner.data.injection

import com.androbrain.qr.scanner.data.calendar_event.CalendarEventDataSource
import com.androbrain.qr.scanner.data.calendar_event.local.CalendarEventDao
import com.androbrain.qr.scanner.data.calendar_event.local.CalendarEventLocalDataSource
import com.androbrain.qr.scanner.data.contact_info.ContactInfoDataSource
import com.androbrain.qr.scanner.data.contact_info.local.ContactInfoDao
import com.androbrain.qr.scanner.data.contact_info.local.ContactInfoLocalDataSource
import com.androbrain.qr.scanner.data.driver_license.DriverLicenseDataSource
import com.androbrain.qr.scanner.data.driver_license.local.DriverLicenseDao
import com.androbrain.qr.scanner.data.driver_license.local.DriverLicenseLocalDataSource
import com.androbrain.qr.scanner.data.email.EmailDataSource
import com.androbrain.qr.scanner.data.email.local.EmailDao
import com.androbrain.qr.scanner.data.email.local.EmailLocalDataSource
import com.androbrain.qr.scanner.data.geo_point.GeoPointDataSource
import com.androbrain.qr.scanner.data.geo_point.local.GeoPointDao
import com.androbrain.qr.scanner.data.geo_point.local.GeoPointLocalDataSource
import com.androbrain.qr.scanner.data.phone.PhoneDataSource
import com.androbrain.qr.scanner.data.phone.local.PhoneDao
import com.androbrain.qr.scanner.data.phone.local.PhoneLocalDataSource
import com.androbrain.qr.scanner.data.sms.SmsDataSource
import com.androbrain.qr.scanner.data.sms.local.SmsDao
import com.androbrain.qr.scanner.data.sms.local.SmsLocalDataSource
import com.androbrain.qr.scanner.data.url.UrlDataSource
import com.androbrain.qr.scanner.data.url.local.UrlDao
import com.androbrain.qr.scanner.data.url.local.UrlLocalDataSource
import com.androbrain.qr.scanner.data.wifi.WifiDataSource
import com.androbrain.qr.scanner.data.wifi.local.WifiDao
import com.androbrain.qr.scanner.data.wifi.local.WifiLocalDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    @Singleton
    fun provideUrlDataSource(
        urlDao: UrlDao,
    ): UrlDataSource = UrlLocalDataSource(urlDao = urlDao)

    @Provides
    @Singleton
    fun provideWifiDataSource(
        wifiDao: WifiDao,
    ): WifiDataSource = WifiLocalDataSource(wifiDao = wifiDao)

    @Provides
    @Singleton
    fun provideSmsDataSource(
        smsDao: SmsDao,
    ): SmsDataSource = SmsLocalDataSource(smsDao = smsDao)

    @Provides
    @Singleton
    fun providePhoneDataSource(
        phoneDao: PhoneDao,
    ): PhoneDataSource = PhoneLocalDataSource(phoneDao = phoneDao)

    @Provides
    @Singleton
    fun provideGeoPointDataSource(
        geoPointDao: GeoPointDao
    ): GeoPointDataSource = GeoPointLocalDataSource(geopointDao = geoPointDao)

    @Provides
    @Singleton
    fun provideEmailDataSource(
        emailDao: EmailDao
    ): EmailDataSource = EmailLocalDataSource(emailDao = emailDao)

    @Provides
    @Singleton
    fun provideDriverLicenseDataSource(
        driverLicenseDao: DriverLicenseDao
    ): DriverLicenseDataSource = DriverLicenseLocalDataSource(driverLicenseDao = driverLicenseDao)

    @Provides
    @Singleton
    fun provideContactInfoDataSource(
        contactInfoDao: ContactInfoDao
    ): ContactInfoDataSource = ContactInfoLocalDataSource(contactInfoDao = contactInfoDao)

    @Provides
    @Singleton
    fun provideCalendarEventDataSource(
        calendarEventDao: CalendarEventDao
    ): CalendarEventDataSource = CalendarEventLocalDataSource(calendarEventDao = calendarEventDao)
}
