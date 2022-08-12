package com.androbrain.qr.scanner.feature.history

import android.content.Context
import android.os.Parcelable
import androidx.navigation.NavController
import org.threeten.bp.LocalDateTime

interface HistoryBarcode : Parcelable {
    val scanDate: LocalDateTime

    val icon: Int

    val subtitle: String

    fun getTitle(context: Context): String

    fun navigateToScreen(navController: NavController)
}
