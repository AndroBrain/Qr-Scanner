package com.androbrain.qr.scanner.data.injection

import com.androbrain.qr.scanner.data.url.UrlDataSource
import com.androbrain.qr.scanner.data.url.local.LocalUrlDataSource
import com.androbrain.qr.scanner.data.url.local.UrlDao
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
        urlDao: UrlDao
    ): UrlDataSource = LocalUrlDataSource(urlDao = urlDao)

}
