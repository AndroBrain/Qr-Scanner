package com.androbrain.qr.scanner.feature.scan.camera

import androidx.camera.core.Preview
import androidx.lifecycle.LifecycleOwner

interface CameraPreview {
    fun setupPreview(lifecycleOwner: LifecycleOwner, surfaceProvider: Preview.SurfaceProvider)
}
