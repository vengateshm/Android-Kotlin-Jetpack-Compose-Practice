package dev.vengateshm.android_kotlin_compose_practice.utils

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Date.toSystemDate(formatStyle: Int = DateFormat.FULL, locale: Locale = Locale.US): String {
    return SimpleDateFormat.getDateInstance(formatStyle, locale).format(this)
}

fun Date.toSystemTime(formatStyle: Int = DateFormat.SHORT, locale: Locale = Locale.US): String {
    return SimpleDateFormat.getTimeInstance(formatStyle, locale).format(this)
}

fun String.toSystemDate(
    inputDateStrPattern: String = "MM/dd/yyyy",
    outputFormatStyle: Int = DateFormat.FULL,
    locale: Locale = Locale.US
): String? {
    val inputFormatter = SimpleDateFormat(inputDateStrPattern, locale)
    val outputFormatter = SimpleDateFormat.getDateInstance(outputFormatStyle, locale)
    return try {
        val convertedDate = inputFormatter.parse(this) ?: return null
        outputFormatter.format(convertedDate)
    } catch (ex: Exception) {
        null
    }
}

fun String.toSystemTime(
    inputTimeStrPattern: String = "hh:mma",
    outputFormatStyle: Int = DateFormat.SHORT,
    locale: Locale = Locale.US
): String? {
    val inputFormatter = SimpleDateFormat(inputTimeStrPattern, locale)
    val outputFormatter = SimpleDateFormat.getTimeInstance(outputFormatStyle, locale)
    return try {
        val convertedDate = inputFormatter.parse(this) ?: return null
        outputFormatter.format(convertedDate)
    } catch (ex: Exception) {
        null
    }
}