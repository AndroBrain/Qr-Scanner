package com.androbrain.qr.scanner.feature.scan.camera

import android.content.Context
import androidx.camera.core.Camera
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.core.TorchState
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import com.google.common.util.concurrent.ListenableFuture
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow

class CameraPreviewImplementation @Inject constructor(
    private val context: Context,
    private val processCameraProvider: ListenableFuture<ProcessCameraProvider>,
    private val preview: Preview,
    private val cameraSelector: CameraSelector,
    private val imageAnalysis: ImageAnalysis,
) : CameraPreview {

    private var camera: Camera? = null
    private val _isTorchSupported = MutableStateFlow(false)
    private val _torchState = MutableStateFlow(false)

    override fun setupPreview(
        lifecycleOwner: LifecycleOwner,
        surfaceProvider: Preview.SurfaceProvider
    ) {
        processCameraProvider.addListener(
            {
                val cameraProvider = processCameraProvider.get()
                preview.setSurfaceProvider(surfaceProvider)
                camera = cameraProvider.bindToLifecycle(
                    lifecycleOwner,
                    cameraSelector,
                    imageAnalysis,
                    preview,
                )
                _isTorchSupported.value = camera?.cameraInfo?.hasFlashUnit() ?: false
                _torchState.value = camera?.cameraInfo?.torchState?.value == TorchState.ON
            },
            ContextCompat.getMainExecutor(context)
        )
    }

    override fun changeTorchState() {
        camera?.let { camera ->
            val newState = !_torchState.value
            camera.cameraControl.enableTorch(newState)
                .addListener({
                    _torchState.value = newState
                }, ContextCompat.getMainExecutor(context))
        }
    }

    override fun torchModes() = _torchState

    override fun supportTorchMode() = _isTorchSupported

}
