package com.androbrain.qr.scanner.feature.activities.scan

import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.common.InputImage
import dagger.hilt.android.internal.ThreadUtil
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow

class QrInputAnalyzer {

    private var barcodeScannerSuccessListener: OnSuccessListener<List<Barcode>>? = null
    private var barcodeScannerFailureListener: OnFailureListener? = null

    fun analyze(image: InputImage) {
        val processTask = BarcodeScanning.getClient().process(image)
        barcodeScannerSuccessListener?.let { onSuccess ->
            processTask.addOnSuccessListener(onSuccess)
        }
        barcodeScannerFailureListener?.let { onFailure ->
            processTask.addOnFailureListener(onFailure)
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
