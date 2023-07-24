package dev.vengateshm.android_kotlin_compose_practice.compose_states

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class ComposeStatesViewModel(
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {
    var stateViewModel by mutableStateOf(0)
    var savedStateHandleState by ViewModelSavedState(savedStateHandle, "ComposeKey", 0)

    fun setViewModelState() {
        stateViewModel++
    }

    fun setSavedStateHandleState() {
        ++savedStateHandleState
    }
}

class ViewModelSavedState<T>(
    private val savedStateHandle: SavedStateHandle,
    private val key: String,
    private val defaultValue: T,
) : ReadWriteProperty<Any?, T> {

    private var _state by mutableStateOf(savedStateHandle.get<T>(key) ?: defaultValue)

    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return _state
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        _state = value
        savedStateHandle.set(key, value)
    }
}