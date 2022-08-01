package com.androbrain.qr.scanner.util.view

import android.widget.Button
import androidx.core.view.isVisible
import com.androbrain.qr.scanner.util.context.copyToClipboard

fun Button.setupCopyButton(contentToCopy: CharSequence?, label: CharSequence? = null) {
    isVisible = contentToCopy != null && contentToCopy.isNotBlank()
    if (contentToCopy != null && contentToCopy.isNotBlank()) {
        setOnClickListener {
            context.copyToClipboard(text = contentToCopy, label = label)
        }
    }
}
