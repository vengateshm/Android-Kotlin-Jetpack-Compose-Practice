package dev.vengateshm.compose_material3.ui_concepts.form_field_viewmodel

data class OnboardingUiState(
  val email: String = "",
  val fullName: String = "",
  val birthDate: String = "",
  val height: String = "",
  val weight: String = "",

  val emailError: String? = null,
  val fullNameError: String? = null,
  val birthDateError: String? = null,
  val heightError: String? = null,
  val weightError: String? = null,

  val isSubmitted: Boolean = false,
)