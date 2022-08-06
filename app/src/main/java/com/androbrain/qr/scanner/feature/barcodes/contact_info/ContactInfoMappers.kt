package com.androbrain.qr.scanner.feature.barcodes.contact_info

import android.content.Context
import com.androbrain.qr.scanner.R
import com.androbrain.qr.scanner.data.contact_info.ContactInfoAddressModel
import com.androbrain.qr.scanner.data.contact_info.ContactInfoEmailModel
import com.androbrain.qr.scanner.data.contact_info.ContactInfoModel
import com.androbrain.qr.scanner.data.contact_info.ContactInfoPhoneModel
import com.androbrain.qr.scanner.feature.barcodes.LocalDateTimeFormatterUtil.asDateTime
import com.androbrain.qr.scanner.feature.barcodes.util.BarcodesUtil
import com.google.mlkit.vision.barcode.common.Barcode

object ContactInfoMappers {
    fun ContactInfoModel.toBarcodeInfoFirst(context: Context) = listOfNotNull(
        BarcodesUtil.getBarcodeInfo(
            title = R.string.barcodes_scan_date,
            content = scanDate.asDateTime(context),
        ),
        BarcodesUtil.getBarcodeInfo(
            title = R.string.barcodes_display,
            content = display,
        ),
        BarcodesUtil.getBarcodeInfo(
            title = R.string.contact_info_title,
            content = title,
        ),
        BarcodesUtil.getBarcodeInfo(
            title = R.string.contact_info_organization,
            content = organization,
        ),
    )

    fun ContactInfoModel.toBarcodeInfoLast() = listOfNotNull(
        BarcodesUtil.getBarcodeInfo(
            title = R.string.barcodes_raw,
            content = display,
        ),
    )

    fun ContactInfoModel.toBarcodeHeadersWithInfos(context: Context) = listOfNotNull(
        BarcodeHeaderWithInfo(
            header = context.getString(R.string.contact_info_header_person),
            info = toBarcodeInfo()
        ),
        BarcodeHeaderWithInfo(
            header = context.getString(R.string.contact_info_header_addresses),
            info = addresses.toBarcodeInfo(context),
        ),
        BarcodeHeaderWithInfo(
            header = context.getString(R.string.contact_info_header_emails),
            info = emails.toBarcodeInfo(),
        ),
        BarcodeHeaderWithInfo(
            header = context.getString(R.string.contact_info_header_phones),
            info = phones.toBarcodeInfo(context),
        ),
        BarcodeHeaderWithInfo(
            header = context.getString(R.string.contact_info_header_urls),
            info = urls.toBarcodeInfo(),
        ),
    )

    private fun ContactInfoModel.toBarcodeInfo() = listOfNotNull(
        BarcodesUtil.getBarcodeInfo(
            title = R.string.contact_info_first_name,
            content = firstName,
        ),
        BarcodesUtil.getBarcodeInfo(
            title = R.string.contact_info_formatted_name,
            content = formattedName,
        ),
        BarcodesUtil.getBarcodeInfo(
            title = R.string.contact_info_last_name,
            content = lastName,
        ),
        BarcodesUtil.getBarcodeInfo(
            title = R.string.contact_info_middle_name,
            content = middleName,
        ),
        BarcodesUtil.getBarcodeInfo(
            title = R.string.contact_info_prefix_name,
            content = prefixName,
        ),
        BarcodesUtil.getBarcodeInfo(
            title = R.string.contact_info_pronunciation_name,
            content = pronunciationName,
        ),
        BarcodesUtil.getBarcodeInfo(
            title = R.string.contact_info_suffix_name,
            content = suffixName,
        ),
    )

    private fun List<ContactInfoAddressModel>.toBarcodeInfo(context: Context) =
        mapNotNull { address ->
            listOfNotNull(
                BarcodesUtil.getBarcodeInfo(
                    title = R.string.contact_info_address_type,
                    content = when (address.type) {
                        Barcode.Address.TYPE_UNKNOWN -> context.getString(R.string.contact_info_address_type_unknown)
                        Barcode.Address.TYPE_WORK -> context.getString(R.string.contact_info_address_type_work)
                        Barcode.Address.TYPE_HOME -> context.getString(R.string.contact_info_address_type_home)
                        else -> null
                    },
                ),
                BarcodesUtil.getBarcodeInfo(
                    title = R.string.contact_info_address_lines,
                    content = buildString {
                        address.addressLines.forEachIndexed { index, line ->
                            append(line)
                            if (index < address.addressLines.size) {
                                append(", ")
                            }
                        }
                    },
                ),
            )
        }.flatten()

    @JvmName("toBarcodeInfoContactInfoEmailModel")
    private fun List<ContactInfoEmailModel>.toBarcodeInfo() = mapNotNull { email ->
        listOfNotNull(
            BarcodesUtil.getBarcodeInfo(
                title = R.string.contact_info_email_subject,
                content = email.subject,
            ),
            BarcodesUtil.getBarcodeInfo(
                title = R.string.contact_info_email_address,
                content = email.address,
            ),
            BarcodesUtil.getBarcodeInfo(
                title = R.string.contact_info_email_body,
                content = email.body,
            ),
        )
    }.flatten()

    @JvmName("toBarcodeInfoContactInfoPhoneModel")
    private fun List<ContactInfoPhoneModel>.toBarcodeInfo(context: Context) = map { phone ->
        listOfNotNull(
            BarcodesUtil.getBarcodeInfo(
                title = R.string.contact_info_phone_number,
                content = phone.number,
            ),
            BarcodesUtil.getBarcodeInfo(
                title = R.string.contact_info_phone_type,
                content = when (phone.type) {
                    Barcode.Phone.TYPE_UNKNOWN -> context.getString(R.string.contact_info_phone_type_unknown)
                    Barcode.Phone.TYPE_WORK -> context.getString(R.string.contact_info_phone_type_work)
                    Barcode.Phone.TYPE_HOME -> context.getString(R.string.contact_info_phone_type_home)
                    Barcode.Phone.TYPE_FAX -> context.getString(R.string.contact_info_phone_type_fax)
                    Barcode.Phone.TYPE_MOBILE -> context.getString(R.string.contact_info_phone_type_mobile)
                    else -> null
                },
            ),
        )
    }.flatten()

    private fun List<String>.toBarcodeInfo() = mapNotNull { link ->
        BarcodesUtil.getBarcodeInfo(
            title = R.string.contact_info_url,
            content = link,
        )
    }
}
