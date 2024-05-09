package dev.vengateshm.compose_material3.api_compose.lifecycle_events

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.yield

private const val TIMER_DELAY_MS = 10L

class TimerViewModel : ViewModel() {

    private val _timerState = MutableStateFlow(TimerState())
    val timerState = _timerState.asStateFlow()

    private var timerJob: Job? = null

    private var startTime = 0L

    fun startTimer() {
        if (timerJob?.isActive != true) {
            startTime = System.currentTimeMillis() - _timerState.value.passedTimeInMillis
            timerJob = viewModelScope.launch {
                while (isActive) {
                    val currentTime = System.currentTimeMillis()
                    val elapsedTime = currentTime - startTime
                    _timerState.value = TimerState(passedTimeInMillis = elapsedTime)
                    delay(TIMER_DELAY_MS)
                    yield()
                }
            }
        }
    }

    fun stopTimer() {
        timerJob?.cancel()
        timerJob = null
    }
}