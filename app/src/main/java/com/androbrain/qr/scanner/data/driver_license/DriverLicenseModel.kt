package com.androbrain.qr.scanner.data.driver_license

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
data class DriverLicenseModel(
    override val scanDate: LocalDateTime,
    val display: String?,
    val raw: String?,
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

    override fun navigateFromHistory(navController: NavController) {
        navController.safeNavigate(
            HistoryFragmentDirections.actionHistoryFragmentToDriverLicenseFragment(this)
        )
    }

    override fun navigateFromScan(navController: NavController) {
        navController.safeNavigate(
            ScanFragmentDirections.actionScanFragmentToDriverLicenseFragment(this)
        )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as DriverLicenseModel

        if (display != other.display) return false
        if (raw != other.raw) return false
        if (addressCity != other.addressCity) return false
        if (addressState != other.addressState) return false
        if (addressStreet != other.addressStreet) return false
        if (addressZip != other.addressZip) return false
        if (birthDate != other.birthDate) return false
        if (documentType != other.documentType) return false
        if (expirationDate != other.expirationDate) return false
        if (firstName != other.firstName) return false
        if (gender != other.gender) return false
        if (issueDate != other.issueDate) return false
        if (issuingCountry != other.issuingCountry) return false
        if (lastName != other.lastName) return false
        if (licenseNumber != other.licenseNumber) return false
        if (middleName != other.middleName) return false

        return true
    }

    override fun hashCode(): Int {
        var result = display?.hashCode() ?: 0
        result = 31 * result + (raw?.hashCode() ?: 0)
        result = 31 * result + (addressCity?.hashCode() ?: 0)
        result = 31 * result + (addressState?.hashCode() ?: 0)
        result = 31 * result + (addressStreet?.hashCode() ?: 0)
        result = 31 * result + (addressZip?.hashCode() ?: 0)
        result = 31 * result + (birthDate?.hashCode() ?: 0)
        result = 31 * result + (documentType?.hashCode() ?: 0)
        result = 31 * result + (expirationDate?.hashCode() ?: 0)
        result = 31 * result + (firstName?.hashCode() ?: 0)
        result = 31 * result + (gender?.hashCode() ?: 0)
        result = 31 * result + (issueDate?.hashCode() ?: 0)
        result = 31 * result + (issuingCountry?.hashCode() ?: 0)
        result = 31 * result + (lastName?.hashCode() ?: 0)
        result = 31 * result + (licenseNumber?.hashCode() ?: 0)
        result = 31 * result + (middleName?.hashCode() ?: 0)
        return result
    }
}
