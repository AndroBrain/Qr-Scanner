package com.androbrain.qr.scanner.data.injection

import com.androbrain.qr.scanner.data.BarcodeRepository
import com.androbrain.qr.scanner.data.DefaultBarcodeRepository
import com.androbrain.qr.scanner.data.calendar_event.CalendarEventDataSource
import com.androbrain.qr.scanner.data.contact_info.ContactInfoDataSource
import com.androbrain.qr.scanner.data.driver_license.DriverLicenseDataSource
import com.androbrain.qr.scanner.data.email.EmailDataSource
import com.androbrain.qr.scanner.data.geo_point.GeoPointDataSource
import com.androbrain.qr.scanner.data.phone.PhoneDataSource
import com.androbrain.qr.scanner.data.sms.SmsDataSource
import com.androbrain.qr.scanner.data.url.UrlDataSource
import com.androbrain.qr.scanner.data.wifi.WifiDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideBarcodeRepository(
        urlDataSource: UrlDataSource,
        wifiDataSource: WifiDataSource,
        smsDataSource: SmsDataSource,
        phoneDataSource: PhoneDataSource,
        geoPointDataSource: GeoPointDataSource,
        emailDataSource: EmailDataSource,
        driverLicenseDataSource: DriverLicenseDataSource,
        contactInfoDataSource: ContactInfoDataSource,
        calendarEventDataSource: CalendarEventDataSource,
    ): BarcodeRepository =
        DefaultBarcodeRepository(
            urlDataSource = urlDataSource,
            wifiDataSource = wifiDataSource,
            smsDataSource = smsDataSource,
            phoneDataSource = phoneDataSource,
            geoPointDataSource = geoPointDataSource,
            emailDataSource = emailDataSource,
            driverLicenseDataSource = driverLicenseDataSource,
            contactInfoDataSource = contactInfoDataSource,
            calendarEventDataSource = calendarEventDataSource,
        )
}
