package com.androbrain.qr.scanner.util.view

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.CalendarContract
import android.provider.ContactsContract
import android.widget.Button
import androidx.core.view.isVisible
import com.androbrain.qr.scanner.data.calendar_event.CalendarEventModel
import com.androbrain.qr.scanner.util.context.copyToClipboard
import org.threeten.bp.ZoneId
import org.threeten.bp.ZoneOffset

fun Button.setupCopyButton(contentToCopy: CharSequence?, label: CharSequence? = null) {
    isVisible = contentToCopy != null && contentToCopy.isNotBlank()
    if (contentToCopy != null && contentToCopy.isNotBlank()) {
        setOnClickListener {
            context.copyToClipboard(text = contentToCopy, label = label)
        }
    }
}

fun Intent.canOpen(context: Context) = resolveActivity(context.packageManager) != null

fun Button.openUrlInBrowser(url: String) {
    val uri = Uri.parse(url)
    val intent = Intent(Intent.ACTION_VIEW, uri)
    if (intent.canOpen(context)) {
        setOnClickListener { context.startActivity(intent) }
    } else {
        val searchIntent = Intent(Intent.ACTION_WEB_SEARCH)
        searchIntent.putExtra(SearchManager.QUERY, url)
        if (searchIntent.canOpen(context)) {
            setOnClickListener { context.startActivity(searchIntent) }
        } else {
            isVisible = false
        }
    }
}

fun Button.sendEmail(
    addresses: Array<String>?,
    subject: String? = "",
) {
    val intent = Intent(Intent.ACTION_SENDTO)
    intent.data = Uri.parse("mailto:")

    intent.putExtra(Intent.EXTRA_EMAIL, addresses)
    intent.putExtra(Intent.EXTRA_SUBJECT, subject)
    if (intent.canOpen(context)) {
        setOnClickListener { context.startActivity(intent) }
    } else {
        isVisible = false
    }
}

fun Button.addContact(
    name: String? = null,
    phone: String? = null,
    email: String? = null,
) {
    val intent = Intent(Intent.ACTION_INSERT)
    intent.type = ContactsContract.Contacts.CONTENT_TYPE

    intent.putExtra(ContactsContract.Intents.Insert.NAME, name)
    intent.putExtra(ContactsContract.Intents.Insert.PHONE, phone)
    intent.putExtra(ContactsContract.Intents.Insert.EMAIL, email)

    if (intent.canOpen(context)) {
        setOnClickListener { context.startActivity(intent) }
    } else {
        isVisible = false
    }
}

fun Button.dialNumber(
    number: String?,
) {
    val intent = Intent(Intent.ACTION_DIAL)
    intent.data = Uri.parse("tel:$number")
    if (intent.canOpen(context)) {
        setOnClickListener { context.startActivity(intent) }
    } else {
        isVisible = false
    }
}

fun Button.sendSms(
    number: String?
) {
    val intent = Intent(Intent.ACTION_VIEW)
    intent.data = Uri.parse("smsto:$number")
    if (intent.canOpen(context)) {
        setOnClickListener { context.startActivity(intent) }
    } else {
        isVisible = false
    }
}

fun Button.addCalendarEvent(calendarEventModel: CalendarEventModel) {
    val endZone = if (calendarEventModel.isEndUtc == true) {
        ZoneOffset.UTC
    } else {
        ZoneId.systemDefault()
    }
    val startZone = if (calendarEventModel.isStartUtc == true) {
        ZoneOffset.UTC
    } else {
        ZoneId.systemDefault()
    }
    val insertCalendarIntent = Intent(Intent.ACTION_INSERT)
        .setData(CalendarContract.Events.CONTENT_URI)
        .putExtra(CalendarContract.Events.TITLE, calendarEventModel.display)
        .putExtra(
            CalendarContract.EXTRA_EVENT_END_TIME,
            calendarEventModel.end?.atZone(endZone)?.toInstant()?.toEpochMilli()
        )
        .putExtra(
            CalendarContract.EXTRA_EVENT_BEGIN_TIME,
            calendarEventModel.start?.atZone(startZone)?.toInstant()?.toEpochMilli()
        )
        .putExtra(
            CalendarContract.Events.DESCRIPTION,
            calendarEventModel.description
        )
        .putExtra(
            CalendarContract.Events.EVENT_LOCATION,
            calendarEventModel.location
        )
        .putExtra(
            CalendarContract.Events.ORGANIZER,
            calendarEventModel.organizer,
        )
        .putExtra(
            CalendarContract.Events.STATUS,
            when (calendarEventModel.status?.lowercase()) {
                "tentative" -> CalendarContract.Events.STATUS_TENTATIVE
                "confirmed" -> CalendarContract.Events.STATUS_CONFIRMED
                "cancelled" -> CalendarContract.Events.STATUS_CANCELED
                else -> null
            },
        )
    if (insertCalendarIntent.canOpen(context)) {
        setOnClickListener { context.startActivity(insertCalendarIntent) }
    } else {
        isVisible = false
    }
}
