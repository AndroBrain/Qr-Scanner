package com.androbrain.qr.scanner.data.phone

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
data class PhoneModel(
    val display: String?,
    val raw: String?,
    val type: Int,
    val number: String?,
) : Parcelable, HistoryBarcode {
    override val icon: Int
        get() = R.drawable.ic_phone
    override val subtitle: String
        get() = display.orEmpty()

    override fun getTitle(context: Context) = if (number.isNullOrBlank()) {
        context.getString(R.string.screen_phone)
    } else {
        number
    }

    override fun navigateFromHistory(navController: NavController) {
        navController.safeNavigate(
            HistoryFragmentDirections.actionHistoryFragmentToPhoneFragment(this)
        )
    }

    override fun navigateFromScan(navController: NavController) {
        navController.safeNavigate(
            ScanFragmentDirections.actionScanFragmentToPhoneFragment(this)
        )
    }

}
