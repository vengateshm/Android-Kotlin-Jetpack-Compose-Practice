package dev.vengateshm.android_kotlin_compose_practice.one_off_events

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class OneOffViewModel : ViewModel() {
    // Channel flow
    // It has a cache by default
    // When app goes to background and come foreground
    // the last event will be triggered
    private val _navigationEventChannel = Channel<NavigationEvent>()
    val navigationEventChannel = _navigationEventChannel.receiveAsFlow()

    // Shared flow
    // It does not cache by default
    // Need to set replay count
    private val _navigationEventSharedFlow = MutableSharedFlow<NavigationEvent>(
        replay = 1
    )
    val navigationEventSharedFlow = _navigationEventSharedFlow.asSharedFlow()

    var isLoggedIn by mutableStateOf(false)
        private set

    var loginState by mutableStateOf(LoginState())
        private set

    fun onLoginClicked() {
        viewModelScope.launch {
            repeat(1000) {
                delay(3L)
                _navigationEventChannel.send(NavigationEvent.CountEvent(it))
            }
        }

        /*viewModelScope.launch {
            loginState = loginState.copy(isLoading = true)
            delay(3000L)
            loginState = loginState.copy(
                isLoading = false,
                isLoggedIn = true
            )
//            _navigationEventChannel.send(NavigationEvent.NavigateToProfile)
//            _navigationEventSharedFlow.emit(NavigationEvent.NavigateToProfile)
        }*/
    }

    fun onNavigatedToProfile() {
        loginState = loginState.copy(isLoggedIn = false)
    }
}

sealed class NavigationEvent {
    data object NavigateToProfile : NavigationEvent()
    data class CountEvent(val count: Int) : NavigationEvent()
}

data class LoginState(
    val isLoading: Boolean = false,
    val isLoggedIn: Boolean = false,
)