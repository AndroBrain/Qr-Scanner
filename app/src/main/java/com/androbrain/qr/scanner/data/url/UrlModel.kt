package com.androbrain.qr.scanner.data.url

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
data class UrlModel(
    override val scanDate: LocalDateTime,
    val display: String?,
    val raw: String,
    val title: String?,
    val url: String?,
) : Parcelable, HistoryBarcode {
    override val icon: Int
        get() = R.drawable.ic_link

    override val subtitle: String
        get() = url.orEmpty()

    override fun getTitle(context: Context) =
        if (title.isNullOrBlank()) {
            context.getString(R.string.screen_url)
        } else {
            title
        }

    override fun navigateToScreen(navController: NavController) {
        navController.safeNavigate(
            NavGraphDirections.actionGlobalToUrlFragment(this)
        )
    }
}
