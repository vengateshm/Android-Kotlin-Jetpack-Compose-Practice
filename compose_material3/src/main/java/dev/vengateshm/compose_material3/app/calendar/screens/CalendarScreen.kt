package dev.vengateshm.compose_material3.app.calendar.screens

sealed class CalendarScreen(val route: String) {
    data object CalendarEvents : CalendarScreen("calendar_events")
    data object AddEvent : CalendarScreen("add_event")
}