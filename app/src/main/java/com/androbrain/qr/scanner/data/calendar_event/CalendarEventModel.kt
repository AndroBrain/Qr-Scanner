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
    val scanDate: LocalDateTime,
    val display: String?,
    val raw: String?,
    val end: LocalDateTime?,
    val isEndUtc: Boolean?,
    val start: LocalDateTime?,
    val isStartUtc: Boolean?,
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

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CalendarEventModel

        if (display != other.display) return false
        if (raw != other.raw) return false
        if (end != other.end) return false
        if (isEndUtc != other.isEndUtc) return false
        if (start != other.start) return false
        if (isStartUtc != other.isStartUtc) return false
        if (description != other.description) return false
        if (location != other.location) return false
        if (organizer != other.organizer) return false
        if (status != other.status) return false
        if (summary != other.summary) return false

        return true
    }

    override fun hashCode(): Int {
        var result = display?.hashCode() ?: 0
        result = 31 * result + (raw?.hashCode() ?: 0)
        result = 31 * result + (end?.hashCode() ?: 0)
        result = 31 * result + (isEndUtc?.hashCode() ?: 0)
        result = 31 * result + (start?.hashCode() ?: 0)
        result = 31 * result + (isStartUtc?.hashCode() ?: 0)
        result = 31 * result + (description?.hashCode() ?: 0)
        result = 31 * result + (location?.hashCode() ?: 0)
        result = 31 * result + (organizer?.hashCode() ?: 0)
        result = 31 * result + (status?.hashCode() ?: 0)
        result = 31 * result + (summary?.hashCode() ?: 0)
        return result
    }
}
