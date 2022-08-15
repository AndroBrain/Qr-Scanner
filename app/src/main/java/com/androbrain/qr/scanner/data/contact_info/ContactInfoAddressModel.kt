package com.androbrain.qr.scanner.data.contact_info

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Keep
@Serializable
@Parcelize
data class ContactInfoAddressModel(
    val type: Int?,
    val addressLines: List<String>,
) : Parcelable
