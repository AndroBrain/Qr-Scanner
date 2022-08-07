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
import kotlinx.serialization.Serializable
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDateTime

@Parcelize
data class EmailModel(
    override val scanDate: LocalDateTime,
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

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as EmailModel

        if (display != other.display) return false
        if (raw != other.raw) return false
        if (address != other.address) return false
        if (body != other.body) return false
        if (subject != other.subject) return false

        return true
    }

    override fun hashCode(): Int {
        var result = display?.hashCode() ?: 0
        result = 31 * result + (raw?.hashCode() ?: 0)
        result = 31 * result + (address?.hashCode() ?: 0)
        result = 31 * result + (body?.hashCode() ?: 0)
        result = 31 * result + (subject?.hashCode() ?: 0)
        return result
    }
}
