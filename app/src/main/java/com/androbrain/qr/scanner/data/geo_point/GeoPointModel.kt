package com.androbrain.qr.scanner.data.geo_point

import android.content.Context
import android.os.Parcelable
import androidx.annotation.Keep
import androidx.navigation.NavController
import com.androbrain.qr.scanner.NavGraphDirections
import com.androbrain.qr.scanner.R
import com.androbrain.qr.scanner.feature.history.HistoryBarcode
import com.androbrain.qr.scanner.util.navigation.safeNavigate
import kotlinx.parcelize.Parcelize
import org.threeten.bp.LocalDateTime

@Keep
@Parcelize
data class GeoPointModel(
    override val scanDate: LocalDateTime,
    val display: String?,
    val raw: String,
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

    override fun navigateToScreen(navController: NavController) {
        navController.safeNavigate(
            NavGraphDirections.actionGlobalToGeoPointFragment(this)
        )
    }
}
