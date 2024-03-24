package dev.vengateshm.compose_material3.app.calendar

interface CalendarEventRepo {
    suspend fun writeEvent(event: CalendarEvent)

    suspend fun deleteEvent(eventId: Long)

    suspend fun updateEvent(event: CalendarEvent)

    suspend fun getEventByDate(date: Long): List<CalendarEvent>
}