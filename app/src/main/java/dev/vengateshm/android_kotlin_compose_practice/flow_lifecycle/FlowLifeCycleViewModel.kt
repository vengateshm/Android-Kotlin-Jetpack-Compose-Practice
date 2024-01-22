package dev.vengateshm.android_kotlin_compose_practice.flow_lifecycle

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

private const val KEY = "kCounter"

class FlowLifeCycleViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {
    var value = savedStateHandle[KEY] ?: 0 // Handle process death
    val counter =
        flow {
            while (true) {
                emit(value++)
                savedStateHandle[KEY] = value
                Log.i("COUNTER_VALUE", "$value")
                delay(1000L)
            }
        }

    //    val stateFlowVariable = savedStateHandle.getStateFlow(KEY, 0)// Hot so if activity / fragment goes to pause state it will continue emitting
    val stateFlowVariable =
        savedStateHandle.getStateFlow(KEY, 0)
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 0),
                // 0 stops immediately, prevents emitting if activity, fragment onPause
                // 5000 screen rotation
                initialValue = 0,
            )

    init {
        viewModelScope.launch {
            while (true) {
                delay(1000L)
                savedStateHandle[KEY] = stateFlowVariable.value + 1
                Log.i("COUNTER_VALUE", "${stateFlowVariable.value}")
            }
        }
    }
}
