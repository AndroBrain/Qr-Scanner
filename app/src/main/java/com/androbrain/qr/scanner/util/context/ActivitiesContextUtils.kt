package com.androbrain.qr.scanner.util.context

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.androbrain.qr.scanner.BuildConfig
import com.androbrain.qr.scanner.R

private const val APP_LINK =
    "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID

object ActivitiesContextUtils {
    fun createReviewAppIntent(context: Context) =
        Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=${context.packageName}"))

    fun createShareAppLinkIntent(context: Context): Intent {
        val appName = context.getString(R.string.app_name)
        val shareIntent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_SUBJECT, appName)
            putExtra(Intent.EXTRA_TEXT, APP_LINK)
        }
        return Intent.createChooser(shareIntent, appName)
    }

}
