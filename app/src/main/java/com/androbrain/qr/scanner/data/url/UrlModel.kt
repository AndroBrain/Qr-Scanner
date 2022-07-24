package com.androbrain.qr.scanner.data.url

import android.content.Context
import android.os.Parcelable
import androidx.navigation.NavController
import com.androbrain.qr.scanner.R
import com.androbrain.qr.scanner.feature.history.HistoryBarcode
import com.androbrain.qr.scanner.feature.history.HistoryFragmentDirections
import com.androbrain.qr.scanner.util.navigation.safeNavigate
import kotlinx.parcelize.Parcelize
import org.threeten.bp.LocalDate

@Parcelize
data class UrlModel(
    val title: String?,
    val url: String?,
    val creationDate: LocalDate,
    val raw: String?,
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

    override fun navigate(navController: NavController) {
        navController.safeNavigate(
            HistoryFragmentDirections.actionHistoryFragmentToUrlFragment(this)
        )
    }
}
