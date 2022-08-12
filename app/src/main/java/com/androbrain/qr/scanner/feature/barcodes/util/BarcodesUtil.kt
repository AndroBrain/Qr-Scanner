package com.androbrain.qr.scanner.feature.barcodes.util

import android.content.Context
import androidx.annotation.StringRes
import com.androbrain.qr.scanner.R
import com.androbrain.qr.scanner.feature.barcodes.model.info.BarcodeInfo
import com.androbrain.qr.scanner.util.context.shareText
import com.google.android.material.appbar.MaterialToolbar

object BarcodesUtil {
    fun getBarcodeInfo(
        @StringRes title: Int,
        content: String?,
    ) = if (content.isNullOrBlank()) {
        null
    } else {
        BarcodeInfo(
            title = title,
            content = content
        )
    }

    fun MaterialToolbar.setupShare(context: Context, raw: String?, subject: String?) {
        menu.findItem(R.id.item_share).apply {
            isVisible = !raw.isNullOrBlank()
            if (raw != null && raw.isNotBlank()) {
                setOnMenuItemClickListener {
                    context.shareText(
                        subject = subject,
                        text = raw
                    )
                    true
                }
            }
        }
    }
}
