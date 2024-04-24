package dev.vengateshm.compose_material3.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

const val DEFAULT_TIME_FORMAT_UTC = "MM/dd/yyyy hh:mm a"
const val MONTH_DAY_NO_YEAR = "MMM d"
const val HR_MIN_FORMAT = "h:mm a"

fun parseDateTime(dateTime: String, format: String): String {
    return dateTime.takeIf { it.isNotEmpty() }
        ?.let {
            val sdf = SimpleDateFormat(DEFAULT_TIME_FORMAT_UTC, Locale.US)
            sdf.parse(it)?.format(format, Locale.US).orEmpty()
        } ?: ""
}

fun Date.format(
    format: String,
    locale: Locale = Locale.US,
    timeZone: TimeZone = TimeZone.getDefault()
): String {
    val simpleDateFormat = SimpleDateFormat(format, locale)
    simpleDateFormat.timeZone = timeZone
    return simpleDateFormat.format(this)
}

fun String.getUTCTimeStampFromString(format: String): Long {
    return try {
        val dateFormat = SimpleDateFormat(format, Locale.US)
        dateFormat.timeZone = TimeZone.getTimeZone("UTC")
        dateFormat.parse(this)?.time ?: -1
    } catch (e: Exception) {
        -1
    }
}