package com.androbrain.qr.scanner.data.sms

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
data class SmsModel(
    override val scanDate: LocalDateTime,
    val display: String?,
    val raw: String,
    val message: String?,
    val phoneNumber: String?,
) : Parcelable, HistoryBarcode {
    override val icon: Int
        get() = R.drawable.ic_sms
    override val subtitle: String
        get() = message.orEmpty()

    override fun getTitle(context: Context) =
        if (phoneNumber.isNullOrBlank()) {
            context.getString(R.string.screen_sms)
        } else {
            phoneNumber
        }

    override fun navigateToScreen(navController: NavController) {
        navController.safeNavigate(
            NavGraphDirections.actionGlobalToSmsFragment(this)
        )
    }
}
