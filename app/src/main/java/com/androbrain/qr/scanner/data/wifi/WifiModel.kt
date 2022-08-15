package com.androbrain.qr.scanner.data.wifi

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
data class WifiModel(
    override val scanDate: LocalDateTime,
    val display: String?,
    val raw: String?,
    val encryptionType: Int,
    val ssid: String?,
    val password: String?,
) : Parcelable, HistoryBarcode {
    override val icon: Int
        get() = R.drawable.ic_wifi
    override val subtitle: String
        get() = if (display.isNullOrBlank()) {
            ssid.orEmpty()
        } else {
            display
        }

    override fun getTitle(context: Context) = context.getString(R.string.screen_wifi)

    override fun navigateToScreen(navController: NavController) {
        navController.safeNavigate(
            NavGraphDirections.actionGlobalToWifiFragment(this)
        )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as WifiModel

        if (display != other.display) return false
        if (raw != other.raw) return false
        if (encryptionType != other.encryptionType) return false
        if (ssid != other.ssid) return false
        if (password != other.password) return false

        return true
    }

    override fun hashCode(): Int {
        var result = display?.hashCode() ?: 0
        result = 31 * result + (raw?.hashCode() ?: 0)
        result = 31 * result + encryptionType
        result = 31 * result + (ssid?.hashCode() ?: 0)
        result = 31 * result + (password?.hashCode() ?: 0)
        return result
    }
}
