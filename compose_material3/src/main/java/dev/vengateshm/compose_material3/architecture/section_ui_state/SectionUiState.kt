package dev.vengateshm.compose_material3.architecture.section_ui_state

sealed class SectionUiState<out T> {
  data object Loading : SectionUiState<Nothing>()
  data class Success<T>(val data: T) : SectionUiState<T>()
  data class Error(val message: String) : SectionUiState<Nothing>()
}