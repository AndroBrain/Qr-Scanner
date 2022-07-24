package com.androbrain.qr.scanner.feature.history

import android.content.Context
import android.os.Parcelable
import androidx.navigation.NavController

interface HistoryBarcode : Parcelable {
    val icon: Int

    val subtitle: String

    fun getTitle(context: Context): String

    fun navigate(navController: NavController)
}
