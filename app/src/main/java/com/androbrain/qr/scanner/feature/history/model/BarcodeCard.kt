package com.androbrain.qr.scanner.feature.history.model

import androidx.navigation.findNavController
import com.androbrain.qr.scanner.R
import com.androbrain.qr.scanner.databinding.CardBarcodeBinding
import com.androbrain.qr.scanner.feature.history.HistoryBarcode
import com.androbrain.qr.scanner.util.model.ViewBindingKotlinModel

class BarcodeCard(
    private val historyBarcode: HistoryBarcode,
) : ViewBindingKotlinModel<CardBarcodeBinding>(R.layout.card_barcode) {
    override fun CardBarcodeBinding.bind() {
        image.setImageResource(historyBarcode.icon)
        textTitle.text = historyBarcode.getTitle(root.context)
        textSubtitle.text = historyBarcode.subtitle
        root.setOnClickListener { historyBarcode.navigate(it.findNavController()) }
    }
}
