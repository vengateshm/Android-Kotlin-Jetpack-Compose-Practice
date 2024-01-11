package dev.vengateshm.android_kotlin_compose_practice.text_field_validaton

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.stateIn

class FormViewModel(
    private val passwordValidator: FormFieldValidator = PasswordValidator()
) : ViewModel() {
    var password by mutableStateOf("")
        private set

    @OptIn(ExperimentalCoroutinesApi::class)
    val passwordValidationState = snapshotFlow {
        password
    }
        .mapLatest { passwordValidator.validate(it) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5_000),
            initialValue = FormFieldValidationResult()
        )


    fun onPasswordChanges(value: String) {
        password = value
    }
}