package com.androbrain.qr.scanner.feature.history.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.androbrain.qr.scanner.R
import com.androbrain.qr.scanner.databinding.CardBarcodeBinding
import com.androbrain.qr.scanner.util.model.ViewBindingKotlinModel

class BarcodeCard(
    @DrawableRes private val icon: Int,
    private val title: String,
    private val onClick: () -> Unit,
) : ViewBindingKotlinModel<CardBarcodeBinding>(R.layout.card_barcode) {
    override fun CardBarcodeBinding.bind() {
        image.setImageResource(icon)
        textTitle.text = title
        root.setOnClickListener { onClick() }
    }
}