package com.androbrain.qr.scanner.feature.scan.camera

import android.content.Context
import android.util.Size
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageCapture
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import com.google.common.util.concurrent.ListenableFuture
import com.google.mlkit.vision.barcode.BarcodeScanner
import com.google.mlkit.vision.barcode.BarcodeScanning
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.util.concurrent.*
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object CameraModule {

    @Provides
    @Singleton
    fun provideProcessCameraProvider(@ApplicationContext context: Context) =
        ProcessCameraProvider.getInstance(context)

    @Provides
    @Singleton
    fun provideBarcodeScanner() = BarcodeScanning.getClient()

    @Provides
    @Singleton
    fun provideQrAnalyzer(barcodeScanner: BarcodeScanner) = QrAnalyzer(barcodeScanner)

    @Provides
    @Singleton
    fun providePreview() = Preview.Builder().build()

    @Provides
    @Singleton
    fun provideCameraSelector() = CameraSelector
        .Builder()
        .requireLensFacing(CameraSelector.LENS_FACING_BACK)
        .build()

    @Provides
    @Singleton
    fun provideImageAnalysis(
        qrAnalyzer: QrAnalyzer,
    ) = ImageAnalysis.Builder()
        .setTargetResolution(Size(1280, 720))
        .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
        .build().apply {
            setAnalyzer(Executors.newSingleThreadExecutor(), qrAnalyzer)
        }

    @Provides
    @Singleton
    fun provideCameraPreview(
        @ApplicationContext context: Context,
        processCameraProvider: ListenableFuture<ProcessCameraProvider>,
        preview: Preview,
        cameraSelector: CameraSelector,
        imageAnalysis: ImageAnalysis,
    ): CameraPreview = CameraPreviewImplementation(
        context = context,
        processCameraProvider = processCameraProvider,
        preview = preview,
        cameraSelector = cameraSelector,
        imageAnalysis = imageAnalysis,
    )
}