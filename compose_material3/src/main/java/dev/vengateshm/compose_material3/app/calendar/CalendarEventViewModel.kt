package dev.vengateshm.compose_material3.app.calendar

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.vengateshm.compose_material3.app.calendar.DateUtils.adjustDateTimeInMillis
import dev.vengateshm.compose_material3.app.calendar.DateUtils.formatDate
import dev.vengateshm.compose_material3.app.calendar.screens.TimeData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CalendarEventViewModel(private val repository: CalendarEventRepo) : ViewModel() {
    private val _uiState = MutableStateFlow<CalendarEventUiState>(CalendarEventUiState.Loading)
    val uiState: StateFlow<CalendarEventUiState> = _uiState

    var dateStringStart by mutableStateOf("")
    var timeStringStart by mutableStateOf("")
    var dateStringEnd by mutableStateOf("")
    var timeStringEnd by mutableStateOf("")

    var startDateInMillis = 0L
        set(value) {
            dateStringStart = formatDate(value)
            field = value
        }
    var endDateInMillis = 0L
        set(value) {
            dateStringEnd = formatDate(value)
            field = value
        }
    var startTime: TimeData? = null
        set(value) {
            timeStringStart = value?.run { "$hour:$min" } ?: ""
            field = value
        }
    var endTime: TimeData? = null
        set(value) {
            timeStringEnd = value?.run { "$hour:$min" } ?: ""
            field = value
        }

    var isStartDateTimeSelected = false

    fun writeEvent(title: String, description: String) {
        viewModelScope.launch {
            _uiState.value = CalendarEventUiState.Loading
            try {
                val event = CalendarEvent(
                    id = 0,
                    title = title,
                    description = description,
                    startTime = adjustDateTimeInMillis(
                        startDateInMillis,
                        startTime?.hour ?: 0,
                        startTime?.min ?: 0
                    ),
                    endTime = adjustDateTimeInMillis(
                        endDateInMillis,
                        endTime?.hour ?: 0,
                        endTime?.min ?: 0
                    )
                )
                repository.writeEvent(event)
            } catch (e: Exception) {
                _uiState.value = CalendarEventUiState.Error(e.message ?: "Unknown Error")
            }
        }
    }

    fun deleteEvent(eventId: Long) {
        viewModelScope.launch {
            _uiState.value = CalendarEventUiState.Loading
            try {
                repository.deleteEvent(eventId)
                //_uiState.value = CalendarEventUiState.Success("Event Deleted")
            } catch (e: Exception) {
                _uiState.value = CalendarEventUiState.Error(e.message ?: "Unknown Error")
            }
        }
    }

//    fun updateEvent(event: CalendarEvent) {
//        viewModelScope.launch {
//            _uiState.value = CalendarEventUiState.Loading
//            try {
//                repository.updateEvent(event)
//                _uiState.value = CalendarEventUiState.Success("Event Updated")
//            } catch (e: Exception) {
//                _uiState.value = CalendarEventUiState.Error(e.message ?: "Unknown Error")
//            }
//        }
//    }

    fun getEventsByDate(date: Long) {
        viewModelScope.launch {
            _uiState.value = CalendarEventUiState.Loading
            val events = repository.getEventByDate(date)
            _uiState.value = CalendarEventUiState.Success(events)
        }
    }
}

sealed class CalendarEventUiState {
    data object Loading : CalendarEventUiState()
    data class Success(val events: List<CalendarEvent>) : CalendarEventUiState()
    data class Error(val message: String) : CalendarEventUiState()
}