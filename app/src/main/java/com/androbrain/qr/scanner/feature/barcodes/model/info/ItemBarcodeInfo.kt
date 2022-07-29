package com.androbrain.qr.scanner.feature.barcodes.model.info

import android.view.View
import com.androbrain.qr.scanner.R
import com.androbrain.qr.scanner.databinding.ItemBarcodeInfoBinding
import com.androbrain.qr.scanner.util.model.ViewBindingKotlinModel

class ItemBarcodeInfo(
    private val input: BarcodeInfo,
    private val onClick: ((View) -> Unit)? = null
) : ViewBindingKotlinModel<ItemBarcodeInfoBinding>(R.layout.item_barcode_info) {
    override fun ItemBarcodeInfoBinding.bind() {
        textTitle.setText(input.title)
        textContent.text = input.content
        root.setOnClickListener(onClick)
    }
}
