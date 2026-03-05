package dev.vengateshm.compose_material3.ui_concepts.form_field_viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class OnboardingViewModel : ViewModel() {

  private val _uiState = MutableStateFlow(OnboardingUiState())
  val uiState: StateFlow<OnboardingUiState> = _uiState

  fun onEmailChange(value: String) {
    _uiState.update { it.copy(email = value) }
  }

  fun onFullNameChange(value: String) {
    _uiState.update { it.copy(fullName = value) }
  }

  fun onBirthDateChange(value: String) {
    _uiState.update { it.copy(birthDate = value) }
  }

  fun onHeightChange(value: String) {
    _uiState.update { it.copy(height = value) }
  }

  fun onWeightChange(value: String) {
    _uiState.update { it.copy(weight = value) }
  }

  fun onConfirmClick() {
    val state = _uiState.value

    val emailError =
      if (state.email.isBlank() || !android.util.Patterns.EMAIL_ADDRESS.matcher(state.email)
          .matches()
      )
        "Invalid email"
      else null

    val nameError =
      if (state.fullName.isBlank())
        "Full name required"
      else null

    val birthDateError =
      if (state.birthDate.isBlank())
        "Birth date required"
      else null

    val heightError =
      if (state.height.toFloatOrNull() == null)
        "Invalid height"
      else null

    val weightError =
      if (state.weight.toFloatOrNull() == null)
        "Invalid weight"
      else null

    _uiState.update {
      it.copy(
        emailError = emailError,
        fullNameError = nameError,
        birthDateError = birthDateError,
        heightError = heightError,
        weightError = weightError,
        isSubmitted = emailError == null &&
            nameError == null &&
            birthDateError == null &&
            heightError == null &&
            weightError == null,
      )
    }
  }
}