package dev.vengateshm.compose_material3.apps.calendar

data class CalendarEvent(
    val id: Long?,
    val title: String?,
    val description: String?,
    val startTime: Long?,
    val endTime: Long?
)
