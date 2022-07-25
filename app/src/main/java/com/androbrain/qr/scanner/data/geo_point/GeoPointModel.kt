package com.androbrain.qr.scanner.data.geo_point

import android.content.Context
import android.os.Parcelable
import androidx.navigation.NavController
import com.androbrain.qr.scanner.R
import com.androbrain.qr.scanner.feature.history.HistoryBarcode
import com.androbrain.qr.scanner.feature.history.HistoryFragmentDirections
import com.androbrain.qr.scanner.feature.scan.ScanFragmentDirections
import com.androbrain.qr.scanner.util.navigation.safeNavigate
import kotlinx.parcelize.Parcelize

@Parcelize
data class GeoPointModel(
    val display: String?,
    val raw: String?,
    val latitude: Double,
    val longitude: Double,
) : Parcelable, HistoryBarcode {
    override val icon: Int
        get() = R.drawable.ic_geo_point
    override val subtitle: String
        get() = "$latitude, $longitude"

    override fun getTitle(context: Context) = if (display.isNullOrBlank()) {
        context.getString(R.string.screen_geo_point)
    } else {
        display
    }

    override fun navigateFromHistory(navController: NavController) {
        navController.safeNavigate(
            HistoryFragmentDirections.actionHistoryFragmentToGeoPointFragment(this)
        )
    }

    override fun navigateFromScan(navController: NavController) {
        navController.safeNavigate(
            ScanFragmentDirections.actionScanFragmentToGeoPointFragment(this)
        )
    }

}
