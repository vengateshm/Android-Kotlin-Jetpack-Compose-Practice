package dev.vengateshm.compose_material3.utils

import android.content.Context
import android.icu.number.Notation
import android.icu.number.NumberFormatter
import android.icu.number.Precision
import android.icu.text.DateIntervalFormat
import android.icu.text.ListFormatter
import android.icu.text.NumberFormat
import android.icu.util.Currency
import android.icu.util.DateInterval
import android.os.Build
import android.telephony.PhoneNumberUtils
import io.michaelrocks.libphonenumber.android.PhoneNumberUtil
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.Locale

fun <T> formatLists(localeCode: String, list: List<T>): String {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        ListFormatter.getInstance(Locale(localeCode)).format(list)
    } else ""
}

fun formatPercents(localeCode: String, percentFraction: Double): String {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        NumberFormat.getPercentInstance(Locale(localeCode)).format(percentFraction)
    } else ""
}

fun formatDateTimes(localeCode: String, localDateTime: LocalDateTime): String {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT).withLocale(Locale(localeCode))
            .format(localDateTime)
    } else ""
}

fun formatDateTimesWithPattern(
    pattern: String,
    localeCode: String,
    localDateTime: LocalDateTime
): String {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        DateTimeFormatter.ofPattern(pattern).withLocale(Locale(localeCode))
            .format(localDateTime)
    } else ""
}

fun formatPhoneNumbers(phoneNumber: String, defaultCountryIso: String): String {
    return PhoneNumberUtils.formatNumber(phoneNumber, defaultCountryIso)
}

fun Context.formatPhoneNumbers(
    phoneNumber: String,
    defaultRegion: String,
    format: PhoneNumberUtil.PhoneNumberFormat
): String {
    val phoneNumberUtil = PhoneNumberUtil.createInstance(this)
    return phoneNumberUtil.format(phoneNumberUtil.parse(phoneNumber, defaultRegion), format)
}

fun formatDecimalPointsValueWithPlaceholders(
    pattern: String,
    localeCode: String,
    value: Float,
    country: String? = null,
): String {
    return pattern.format(
        if (country != null) Locale(localeCode, country) else Locale(localeCode),
        value
    )
}

fun formatCurrency(localeCode: String, isoCode: String, number: Int): String {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        val format = NumberFormatter.withLocale(Locale(localeCode))
            .unit(Currency.getInstance(isoCode))
            .format(number)
        format.toString()
    } else ""
}

fun formatCurrencyWithNotation(
    localeCode: String,
    isoCode: String,
    fractionPlaces: Int,
    number: Long,
    notation: Notation? = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        Notation.compactShort()
    } else null,
): String {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        val format = NumberFormatter.withLocale(Locale(localeCode))
            .notation(notation)
            .unit(if (isoCode.isNotEmpty()) Currency.getInstance(isoCode) else null)
            .precision(if (fractionPlaces != 0) Precision.maxFraction(fractionPlaces) else null)
            .format(number)
        format.toString()
    } else ""
}

fun formatDateIntervals(dateInterval: DateInterval, skeleton: String, localeCode: String): String {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        DateIntervalFormat.getInstance(skeleton, Locale(localeCode)).format(dateInterval)
    } else ""
}