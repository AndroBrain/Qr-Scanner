package com.androbrain.qr.scanner.data.text

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
data class TextModel(
    val scanDate: LocalDate,
    val display: String?,
    val raw: String?,
) : Parcelable, HistoryBarcode {
    override val icon: Int
        get() = R.drawable.ic_text
    override val subtitle: String
        get() = display.orEmpty()

    override fun getTitle(context: Context) = context.getString(R.string.screen_text)

    override fun navigateFromHistory(navController: NavController) {
        navController.safeNavigate(
            HistoryFragmentDirections.actionHistoryFragmentToTextFragment(this)
        )
    }

    override fun navigateFromScan(navController: NavController) {
        navController.safeNavigate(
            ScanFragmentDirections.actionScanFragmentToTextFragment(this)
        )
    }
}