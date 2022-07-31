package com.androbrain.qr.scanner.data.sms

import android.content.Context
import android.os.Parcelable
import androidx.navigation.NavController
import com.androbrain.qr.scanner.R
import com.androbrain.qr.scanner.feature.history.HistoryBarcode
import com.androbrain.qr.scanner.feature.history.HistoryFragmentDirections
import com.androbrain.qr.scanner.feature.scan.ScanFragmentDirections
import com.androbrain.qr.scanner.util.navigation.safeNavigate
import kotlinx.parcelize.Parcelize
import org.threeten.bp.LocalDate

@Parcelize
data class SmsModel(
    val scanDate: LocalDate,
    val display: String?,
    val raw: String?,
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

    override fun navigateFromHistory(navController: NavController) {
        navController.safeNavigate(
            HistoryFragmentDirections.actionHistoryFragmentToSmsFragment(this)
        )
    }

    override fun navigateFromScan(navController: NavController) {
        navController.safeNavigate(
            ScanFragmentDirections.actionScanFragmentToSmsFragment(this)
        )
    }
}
