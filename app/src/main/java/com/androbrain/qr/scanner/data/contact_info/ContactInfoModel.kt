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
    val raw: String?,
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

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ContactInfoModel

        if (display != other.display) return false
        if (raw != other.raw) return false
        if (organization != other.organization) return false
        if (title != other.title) return false
        if (firstName != other.firstName) return false
        if (formattedName != other.formattedName) return false
        if (lastName != other.lastName) return false
        if (middleName != other.middleName) return false
        if (prefixName != other.prefixName) return false
        if (pronunciationName != other.pronunciationName) return false
        if (suffixName != other.suffixName) return false
        if (addresses != other.addresses) return false
        if (emails != other.emails) return false
        if (phones != other.phones) return false
        if (urls != other.urls) return false

        return true
    }

    override fun hashCode(): Int {
        var result = display?.hashCode() ?: 0
        result = 31 * result + (raw?.hashCode() ?: 0)
        result = 31 * result + (organization?.hashCode() ?: 0)
        result = 31 * result + (title?.hashCode() ?: 0)
        result = 31 * result + (firstName?.hashCode() ?: 0)
        result = 31 * result + (formattedName?.hashCode() ?: 0)
        result = 31 * result + (lastName?.hashCode() ?: 0)
        result = 31 * result + (middleName?.hashCode() ?: 0)
        result = 31 * result + (prefixName?.hashCode() ?: 0)
        result = 31 * result + (pronunciationName?.hashCode() ?: 0)
        result = 31 * result + (suffixName?.hashCode() ?: 0)
        result = 31 * result + addresses.hashCode()
        result = 31 * result + emails.hashCode()
        result = 31 * result + phones.hashCode()
        result = 31 * result + urls.hashCode()
        return result
    }
}
