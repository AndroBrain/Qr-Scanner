package com.androbrain.qr.scanner.feature.barcodes.model.info

import android.view.View
import com.androbrain.qr.scanner.R
import com.androbrain.qr.scanner.databinding.CardInfoBinding
import com.androbrain.qr.scanner.util.model.ViewBindingKotlinModel

class InfoCard(
    private val input: InfoCardInput,
    private val onClick: ((View) -> Unit)? = null
) : ViewBindingKotlinModel<CardInfoBinding>(R.layout.card_info) {
    override fun CardInfoBinding.bind() {
        textTitle.setText(input.title)
        textContent.text = input.content
        root.setOnClickListener(onClick)
    }
}
