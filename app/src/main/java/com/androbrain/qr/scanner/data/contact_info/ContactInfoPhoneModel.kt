package com.androbrain.qr.scanner.data.contact_info

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class ContactInfoPhoneModel(
    val type: Int,
    val number: String?,
) : Parcelable
