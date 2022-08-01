package com.androbrain.qr.scanner.feature.barcodes.contact_info

import android.view.View
import com.airbnb.epoxy.EpoxyController
import com.androbrain.qr.scanner.feature.barcodes.model.header.ItemBarcodeHeader
import com.androbrain.qr.scanner.feature.barcodes.model.info.BarcodeInfo
import com.androbrain.qr.scanner.feature.barcodes.model.info.ItemBarcodeInfo

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

    var onCardClick: ((View) -> Unit)? = null

    override fun buildModels() {
        var idCounter = 1
        firstInfo?.forEach { barcodeInfo ->
            ItemBarcodeInfo(
                input = barcodeInfo,
                onClick = onCardClick,
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
                        onClick = onCardClick,
                    ).id(idCounter).addTo(this)
                    idCounter++
                }
            }
        }

        lastInfo?.forEach { barcodeInfo ->
            ItemBarcodeInfo(
                input = barcodeInfo,
                onClick = onCardClick,
            ).id(idCounter).addTo(this)
            idCounter++
        }
    }
}
