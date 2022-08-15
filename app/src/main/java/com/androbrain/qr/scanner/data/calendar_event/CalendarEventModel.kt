package com.androbrain.qr.scanner.data.calendar_event

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
data class CalendarEventModel(
    override val scanDate: LocalDateTime,
    val display: String?,
    val raw: String,
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

    override fun navigateToScreen(navController: NavController) {
        navController.safeNavigate(
            NavGraphDirections.actionGlobalToCalendarEventFragment(this)
        )
    }
}
