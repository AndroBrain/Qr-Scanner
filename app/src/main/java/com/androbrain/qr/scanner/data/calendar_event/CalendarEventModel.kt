package com.androbrain.qr.scanner.data.calendar_event

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
data class CalendarEventModel(
    val scanDate: LocalDate,
    val display: String?,
    val raw: String?,
    val end: LocalDateTime?,
    val start: LocalDateTime?,
    val description: String?,
    val location: String?,
    val organizer: String?,
    val status: String?,
    val summary: String?,
) : Parcelable, HistoryBarcode {
    override val icon: Int
        get() = R.drawable.ic_calendar_event
    override val subtitle: String
        get() = if (display.isNullOrBlank()) {
            organizer.orEmpty()
        } else {
            display
        }

    override fun getTitle(context: Context) = context.getString(R.string.screen_calendar_event)

    override fun navigateFromHistory(navController: NavController) {
        navController.safeNavigate(
            HistoryFragmentDirections.actionHistoryFragmentToCalendarEventFragment(this)
        )
    }

    override fun navigateFromScan(navController: NavController) {
        navController.safeNavigate(
            ScanFragmentDirections.actionScanFragmentToCalendarEventFragment(this)
        )
    }

}
