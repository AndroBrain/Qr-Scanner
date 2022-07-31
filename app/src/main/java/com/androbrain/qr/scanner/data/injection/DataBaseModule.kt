package com.androbrain.qr.scanner.data.injection

import android.content.Context
import androidx.room.Room
import com.androbrain.qr.scanner.R
import com.androbrain.qr.scanner.data.core.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            context.getString(R.string.database_name)
        )
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    fun provideUrlDao(database: AppDatabase) = database.urlDao()

    @Provides
    @Singleton
    fun provideWifiDao(database: AppDatabase) = database.wifiDao()

    @Provides
    @Singleton
    fun provideSmsDao(database: AppDatabase) = database.smsDao()

    @Provides
    @Singleton
    fun providePhoneDao(database: AppDatabase) = database.phoneDao()

    @Provides
    @Singleton
    fun provideGeoPointDao(database: AppDatabase) = database.getPointDao()

    @Provides
    @Singleton
    fun provideEmailDao(database: AppDatabase) = database.getEmailDao()

    @Provides
    @Singleton
    fun provideDriverLicenseDao(database: AppDatabase) = database.getDriverLicenseDao()

    @Provides
    @Singleton
    fun provideContactInfoDao(database: AppDatabase) = database.getContactInfoDao()

    @Provides
    @Singleton
    fun provideCalendarEventDao(database: AppDatabase) = database.getCalendarEventDao()

    @Provides
    @Singleton
    fun provideTextDao(database: AppDatabase) = database.textDao()
}
