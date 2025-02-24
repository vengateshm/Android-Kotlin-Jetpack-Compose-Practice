package dev.vengateshm.compose_material3.api_kotlin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

data class RegisterState(
    val email: String = "",
    val password: String = "",
    val isEmailValid: Boolean = false,
    val isPasswordValid: Boolean = false,
    val canRegister: Boolean = false,
)

class SingleStateViewModel : ViewModel() {
    private val _emailState = MutableStateFlow("")
    val emailState = _emailState.asStateFlow()

    private val _passwordState = MutableStateFlow("")
    val passwordState = _passwordState.asStateFlow()

    val isValidEmail = emailState
        .map { it.contains("@") }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            false,
        )

    val isValidPassword = passwordState
        .map { it.length >= 8 }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            false,
        )

    val canRegister = combine(isValidEmail, isValidPassword)
    { isValidEmail, isValidPassword ->
        isValidEmail && isValidPassword
    }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            false,
        )

    fun updateEmail(email: String) {
        _emailState.update { email }
    }

    fun updatePassword(password: String) {
        _passwordState.update { password }
    }
}

class StateDataClassViewModel : ViewModel() {
    private val _registerState = MutableStateFlow(RegisterState())
    val registerState = _registerState.asStateFlow()

    init {
        registerState
            .distinctUntilChangedBy { it.email }
            .map { it.email.contains("@") }
            .onEach { isEmailValid ->
                _registerState.update {
                    it.copy(isEmailValid = isEmailValid)
                }
            }.launchIn(viewModelScope)

        registerState
            .distinctUntilChangedBy { it.password }
            .map { it.password.length >= 8 }
            .onEach { isPasswordValid ->
                _registerState.update {
                    it.copy(isPasswordValid = isPasswordValid)
                }
            }.launchIn(viewModelScope)

        registerState
            .onEach { state ->
                _registerState.update {
                    it.copy(canRegister = state.isEmailValid && state.isPasswordValid)
                }
            }.launchIn(viewModelScope)
    }

    fun updateEmail(email: String) {
        _registerState.update {
            it.copy(email = email)
        }
    }

    fun updatePassword(password: String) {
        _registerState.update {
            it.copy(password = password)
        }
    }
}