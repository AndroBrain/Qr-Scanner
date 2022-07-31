package com.androbrain.qr.scanner.feature.barcodes.contact_info

import android.view.View
import androidx.annotation.CheckResult
import com.airbnb.epoxy.EpoxyController
import com.androbrain.qr.scanner.feature.barcodes.model.header.ItemBarcodeHeader
import com.androbrain.qr.scanner.feature.barcodes.model.info.BarcodeInfo
import com.androbrain.qr.scanner.feature.barcodes.model.info.ItemBarcodeInfo
import dagger.hilt.android.internal.ThreadUtil
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow

class ContactInfoController : EpoxyController() {
    var headersWithInfo: List<BarcodeHeaderWithInfo>? = null
        set(value) {
            field = value
            requestModelBuild()
        }

    var firstInfo: List<BarcodeInfo>? = null
        set(value) {
            field = value
            requestModelBuild()
        }

    var lastInfo: List<BarcodeInfo>? = null
        set(value) {
            field = value
            requestModelBuild()
        }

    private var onCardClick: ((View) -> Unit)? = null

    override fun buildModels() {
        var idCounter = 1
        firstInfo?.forEach { barcodeInfo ->
            ItemBarcodeInfo(
                input = barcodeInfo,
                onClick = { onCardClick?.invoke(it) },
            ).id(idCounter).addTo(this)
            idCounter++
        }

        headersWithInfo?.forEach { headerWithInfo ->
            if (headerWithInfo.info.isNotEmpty()) {
                ItemBarcodeHeader(
                    headerWithInfo.header
                ).id(idCounter).addTo(this)
                idCounter++

                headerWithInfo.info.forEach { barcodeInfo ->
                    ItemBarcodeInfo(
                        input = barcodeInfo,
                        onClick = { onCardClick?.invoke(it) },
                    ).id(idCounter).addTo(this)
                    idCounter++
                }
            }
        }

        lastInfo?.forEach { barcodeInfo ->
            ItemBarcodeInfo(
                input = barcodeInfo,
                onClick = { onCardClick?.invoke(it) },
            ).id(idCounter).addTo(this)
            idCounter++
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
