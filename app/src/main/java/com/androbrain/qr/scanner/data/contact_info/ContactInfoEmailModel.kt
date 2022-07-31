package com.androbrain.qr.scanner.data.contact_info

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class ContactInfoEmailModel(
    val address: String?,
    val body: String?,
    val subject: String?,
) : Parcelable
