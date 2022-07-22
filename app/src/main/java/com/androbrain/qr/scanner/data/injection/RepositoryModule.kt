package com.androbrain.qr.scanner.data.injection

import com.androbrain.qr.scanner.data.BarcodeRepository
import com.androbrain.qr.scanner.data.DefaultBarcodeRepository
import com.androbrain.qr.scanner.data.url.UrlDataSource
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
    fun provideBarcodeRepository(urlDataSource: UrlDataSource): BarcodeRepository =
        DefaultBarcodeRepository(urlDataSource = urlDataSource)
}
