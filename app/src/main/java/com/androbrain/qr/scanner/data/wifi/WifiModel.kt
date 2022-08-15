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
    val raw: String,
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
}
