package com.androbrain.qr.scanner.feature.barcodes.controller

import android.view.View
import androidx.annotation.CheckResult
import com.airbnb.epoxy.EpoxyController
import com.androbrain.qr.scanner.feature.barcodes.model.info.ItemBarcodeInfo
import com.androbrain.qr.scanner.feature.barcodes.model.info.BarcodeInfo
import dagger.hilt.android.internal.ThreadUtil
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow

class BarcodeController : EpoxyController() {

    var info: List<BarcodeInfo>? = null
        set(value) {
            field = value
            requestModelBuild()
        }

    private var onCardClick: ((View) -> Unit)? = null

    override fun buildModels() {
        info?.forEachIndexed { index, infoCardInput ->
            ItemBarcodeInfo(
                input = infoCardInput,
                onClick = {

                },
            ).id(index).addTo(this)
        }
    }

    @CheckResult
    fun onCardClicks() = callbackFlow {
        ThreadUtil.ensureMainThread()

        onCardClick = {
            trySend(it)
        }

        awaitClose { onCardClick = null }
    }
}
