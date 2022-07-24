package com.androbrain.qr.scanner.feature.scan.camera

import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.mlkit.vision.barcode.BarcodeScanner
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.common.InputImage
import dagger.hilt.android.internal.ThreadUtil
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class QrAnalyzer @Inject constructor(
    private val barcodeScanner: BarcodeScanner
) : ImageAnalysis.Analyzer {

    private var barcodeScannerSuccessListener: OnSuccessListener<List<Barcode>>? = null
    private var barcodeScannerFailureListener: OnFailureListener? = null

    override fun analyze(imageProxy: ImageProxy) {
        val mediaImage = imageProxy.image ?: return
        val image = InputImage.fromMediaImage(mediaImage, imageProxy.imageInfo.rotationDegrees)
        val processTask = barcodeScanner.process(image)
        barcodeScannerSuccessListener?.let { onSuccess ->
            processTask.addOnSuccessListener(onSuccess)
        }
        barcodeScannerFailureListener?.let { onFailure ->
            processTask.addOnFailureListener(onFailure)
        }
        processTask.addOnCompleteListener {
            imageProxy.close()
        }
    }

    fun successesFlow() = callbackFlow {
        ThreadUtil.ensureMainThread()

        barcodeScannerSuccessListener = OnSuccessListener { barcodes ->
            if (barcodes.size == 1) {
                trySend(barcodes[0])
            }
        }

        awaitClose {
            barcodeScannerSuccessListener = null
        }
    }

    fun failuresFlow() = callbackFlow {
        ThreadUtil.ensureMainThread()

        barcodeScannerFailureListener = OnFailureListener {
            trySend(it)
        }

        awaitClose {
            barcodeScannerFailureListener = null
        }
    }
}
