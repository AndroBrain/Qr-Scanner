package com.androbrain.qr.scanner.feature.barcodes.controller

import android.view.View
import com.airbnb.epoxy.EpoxyController
import com.androbrain.qr.scanner.feature.barcodes.model.info.BarcodeInfo
import com.androbrain.qr.scanner.feature.barcodes.model.info.ItemBarcodeInfo

class BarcodeController : EpoxyController() {

    var info: List<BarcodeInfo>? = null
        set(value) {
            field = value
            requestModelBuild()
        }

    var onCardClick: ((View) -> Unit)? = null

    override fun buildModels() {
        info?.forEachIndexed { index, infoCardInput ->
            ItemBarcodeInfo(
                input = infoCardInput,
                onClick = onCardClick,
            ).id(index).addTo(this)
        }
    }
}
