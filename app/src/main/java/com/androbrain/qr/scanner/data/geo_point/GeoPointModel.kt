package com.androbrain.qr.scanner.data.geo_point

import android.content.Context
import android.os.Parcelable
import androidx.navigation.NavController
import com.androbrain.qr.scanner.NavGraphDirections
import com.androbrain.qr.scanner.R
import com.androbrain.qr.scanner.feature.history.HistoryBarcode
import com.androbrain.qr.scanner.util.navigation.safeNavigate
import kotlinx.parcelize.Parcelize
import org.threeten.bp.LocalDateTime

@Parcelize
data class GeoPointModel(
    override val scanDate: LocalDateTime,
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

    override fun navigateToScreen(navController: NavController) {
        navController.safeNavigate(
            NavGraphDirections.actionGlobalToGeoPointFragment(this)
        )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as GeoPointModel

        if (display != other.display) return false
        if (raw != other.raw) return false
        if (latitude != other.latitude) return false
        if (longitude != other.longitude) return false

        return true
    }

    override fun hashCode(): Int {
        var result = display?.hashCode() ?: 0
        result = 31 * result + (raw?.hashCode() ?: 0)
        result = 31 * result + latitude.hashCode()
        result = 31 * result + longitude.hashCode()
        return result
    }
}
