package dev.vengateshm.compose_material3.app.calendar

import android.os.Build
import androidx.annotation.RequiresApi
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId
import java.util.Calendar
import java.util.Date
import java.util.Locale

object DateUtils {
    fun formatStartDate(event: CalendarEvent): String {
        val sdf = SimpleDateFormat("dd MMM, yyyy", Locale.getDefault())
        return event.startTime?.let { sdf.format(Date(it)) } ?: ""
    }

    fun formatEventTime(event: CalendarEvent): String {
        // Format start and end times as needed, for example:
        val startTime = DateFormat.getTimeInstance(DateFormat.SHORT).format(event.startTime)
        val endTime = DateFormat.getTimeInstance(DateFormat.SHORT).format(event.endTime)
        return "Starts: $startTime - Ends: $endTime"
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun convertToMillis(date: LocalDate, time: LocalTime): Long {
        return LocalDateTime.of(date, time).atZone(ZoneId.systemDefault()).toEpochSecond() * 1000L
    }

    fun formatDate(milliseconds: Long): String {
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        return dateFormat.format(Date(milliseconds))
    }

    fun adjustDateTimeInMillis(
        dateTimeInMillis: Long,
        hour: Int,
        minute: Int
    ): Long {
        val calendar = Calendar.getInstance().apply {
            timeInMillis = dateTimeInMillis
            set(Calendar.HOUR_OF_DAY, hour)
            set(Calendar.MINUTE, minute)
        }
        return calendar.timeInMillis
    }
}