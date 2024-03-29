package com.androbrain.qr.scanner.data.driver_license

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
data class DriverLicenseModel(
    override val scanDate: LocalDateTime,
    val display: String?,
    val raw: String,
    val addressCity: String?,
    val addressState: String?,
    val addressStreet: String?,
    val addressZip: String?,
    val birthDate: String?,
    val documentType: String?,
    val expirationDate: String?,
    val firstName: String?,
    val gender: String?,
    val issueDate: String?,
    val issuingCountry: String?,
    val lastName: String?,
    val licenseNumber: String?,
    val middleName: String?,
) : Parcelable, HistoryBarcode {
    override val icon: Int
        get() = R.drawable.ic_driver_license
    override val subtitle: String
        get() = if (licenseNumber.isNullOrBlank()) {
            expirationDate.orEmpty()
        } else {
            licenseNumber
        }

    override fun getTitle(context: Context) = context.getString(R.string.screen_driver_license)

    override fun navigateToScreen(navController: NavController) {
        navController.safeNavigate(
            NavGraphDirections.actionGlobalToDriverLicenseFragment(this)
        )
    }
}
