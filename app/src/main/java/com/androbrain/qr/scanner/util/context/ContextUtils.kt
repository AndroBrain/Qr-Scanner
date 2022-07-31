package com.androbrain.qr.scanner.util.context

import android.app.SearchManager
import android.content.ActivityNotFoundException
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.widget.Toast
import com.androbrain.qr.scanner.R

fun Context.shareText(subject: CharSequence?, text: CharSequence, title: CharSequence? = null) {
    val sendIntent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_SUBJECT, subject)
        putExtra(Intent.EXTRA_TEXT, text)
        type = "text/plain"
    }

    val shareIntent = Intent.createChooser(sendIntent, title)
    startActivity(shareIntent)
}

fun Context.openUrlInBrowser(url: String) {
    try {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
    } catch (e: ActivityNotFoundException) {
        val searchIntent = Intent(Intent.ACTION_WEB_SEARCH)
        searchIntent.putExtra(SearchManager.QUERY, url)
        startActivity(searchIntent)
    }
}

fun Context.copyToClipboard(
    text: CharSequence,
    label: CharSequence? = getString(R.string.text_copy)
) {
    val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    val properLabel = if (label.isNullOrBlank()) {
        getString(R.string.text_copy)
    } else {
        label
    }
    val clip = ClipData.newPlainText(properLabel, text)
    clipboard.setPrimaryClip(clip)
    if (Build.VERSION.SDK_INT < 31) {
        Toast.makeText(this, R.string.copied_to_clipboard, Toast.LENGTH_SHORT).show()
    }
}
