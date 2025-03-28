package dev.vengateshm.xml_kotlin.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDateTime
import java.time.Month
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeFormatterBuilder
import java.time.temporal.ChronoField
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
fun String.convertStringToDateTimeUS(format: String, acceptAmPmIsLowerCase: Boolean = false) =
    try {
        LocalDateTime.parse(this, format.toSimpleDateFormatUS(acceptAmPmIsLowerCase))
    } catch (e: Exception) {
        null
    }

@RequiresApi(Build.VERSION_CODES.O)
fun String.toSimpleDateFormatUS(acceptAmPmIsLowerCase: Boolean = false): DateTimeFormatter =
    if (this.contains('a') && acceptAmPmIsLowerCase) {
        DateTimeFormatterBuilder().appendPattern(this.replace("a", ""))
            .appendText(ChronoField.AMPM_OF_DAY, mapOf(0L to "am", 1L to "pm")).toFormatter()
    } else {
        DateTimeFormatter.ofPattern(this, Locale.US)
    }

@RequiresApi(Build.VERSION_CODES.O)
fun LocalDateTime.format(formatString: String, locale: Locale = Locale.US) =
    try {
        val formatStringAdjusted = if (
            formatString == DATE_FORMAT_W_DOT_COMMA_FOR_DAY_NO_LEADING_ZERO
            && this.month == Month.MAY
        ) {
            DATE_FORMAT_W_DOT_COMMA_FOR_DAY_NO_DOT_MAY_NO_LEADING_ZERO
        } else {
            formatString
        }
        DateTimeFormatter.ofPattern(formatStringAdjusted, locale).format(this)
    } catch (e: Exception) {
        null
    }