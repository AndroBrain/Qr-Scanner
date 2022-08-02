package com.androbrain.qr.scanner.feature.scan.camera

import androidx.camera.core.Preview
import androidx.lifecycle.LifecycleOwner
import kotlinx.coroutines.flow.Flow

interface CameraPreview {
    fun setupPreview(lifecycleOwner: LifecycleOwner, surfaceProvider: Preview.SurfaceProvider)
    fun changeTorchState()
    fun torchModes(): Flow<Boolean>
    fun supportTorchMode(): Flow<Boolean>
}
