package com.androbrain.qr.scanner.data.email

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
data class EmailModel(
    val scanDate: LocalDate,
    val display: String?,
    val raw: String?,
    val address: String?,
    val body: String?,
    val subject: String?,
) : Parcelable, HistoryBarcode {
    override val icon: Int
        get() = R.drawable.ic_email
    override val subtitle: String
        get() = if (subject.isNullOrBlank()) {
            address.orEmpty()
        } else {
            subject
        }

    override fun getTitle(context: Context) = if (address.isNullOrBlank()) {
        context.getString(R.string.screen_email)
    } else {
        address
    }

    override fun navigateFromHistory(navController: NavController) {
        navController.safeNavigate(
            HistoryFragmentDirections.actionHistoryFragmentToEmailFragment(this)
        )
    }

    override fun navigateFromScan(navController: NavController) {
        navController.safeNavigate(
            ScanFragmentDirections.actionScanFragmentToEmailFragment(this)
        )
    }

}
