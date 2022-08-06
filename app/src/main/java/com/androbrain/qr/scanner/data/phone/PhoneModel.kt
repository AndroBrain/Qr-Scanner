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
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDateTime

@Parcelize
data class PhoneModel(
    val scanDate: LocalDateTime,
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

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as PhoneModel

        if (display != other.display) return false
        if (raw != other.raw) return false
        if (type != other.type) return false
        if (number != other.number) return false

        return true
    }

    override fun hashCode(): Int {
        var result = display?.hashCode() ?: 0
        result = 31 * result + (raw?.hashCode() ?: 0)
        result = 31 * result + type
        result = 31 * result + (number?.hashCode() ?: 0)
        return result
    }
}
