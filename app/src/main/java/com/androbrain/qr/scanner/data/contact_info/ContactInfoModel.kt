package com.androbrain.qr.scanner.data.contact_info

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
data class ContactInfoModel(
    override val scanDate: LocalDateTime,
    val display: String?,
    val raw: String,
    val organization: String?,
    val title: String?,
    val firstName: String?,
    val formattedName: String?,
    val lastName: String?,
    val middleName: String?,
    val prefixName: String?,
    val pronunciationName: String?,
    val suffixName: String?,
    val addresses: List<ContactInfoAddressModel>,
    val emails: List<ContactInfoEmailModel>,
    val phones: List<ContactInfoPhoneModel>,
    val urls: List<String>,
) : Parcelable, HistoryBarcode {
    override val icon: Int
        get() = R.drawable.ic_contact_info
    override val subtitle: String
        get() = if (title.isNullOrBlank()) {
            display.orEmpty()
        } else {
            title
        }

    override fun getTitle(context: Context) = context.getString(R.string.screen_contact_info)

    override fun navigateToScreen(navController: NavController) {
        navController.safeNavigate(
            NavGraphDirections.actionGlobalToContactInfoFragment(this)
        )
    }
}
