package com.androbrain.qr.scanner.feature.barcodes.model.header

import com.androbrain.qr.scanner.R
import com.androbrain.qr.scanner.databinding.ItemBarcodeHeaderBinding
import com.androbrain.qr.scanner.util.model.ViewBindingKotlinModel

class ItemBarcodeHeader(private val header: CharSequence) :
    ViewBindingKotlinModel<ItemBarcodeHeaderBinding>(R.layout.item_barcode_header) {
    override fun ItemBarcodeHeaderBinding.bind() {
        root.text = header
    }
}
