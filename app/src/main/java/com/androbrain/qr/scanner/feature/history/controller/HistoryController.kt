package com.androbrain.qr.scanner.feature.history.controller

import com.airbnb.epoxy.EpoxyController
import com.androbrain.qr.scanner.feature.history.HistoryBarcode
import com.androbrain.qr.scanner.feature.history.model.BarcodeCard

class HistoryController : EpoxyController() {

    var barcodes: List<HistoryBarcode>? = null
        set(value) {
            field = value
            requestModelBuild()
        }

    override fun buildModels() {
        barcodes?.forEachIndexed { index, historyBarcode ->
            BarcodeCard(historyBarcode)
                .id(index)
                .addTo(this)
        }
    }
}
