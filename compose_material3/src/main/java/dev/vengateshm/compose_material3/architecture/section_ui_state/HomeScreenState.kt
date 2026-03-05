package dev.vengateshm.compose_material3.architecture.section_ui_state

data class HomeScreenState(
  val items: SectionUiState<List<String>> = SectionUiState.Loading,
  val topRated: SectionUiState<List<String>> = SectionUiState.Loading,
  val notes: SectionUiState<List<String>> = SectionUiState.Loading,
  val cards: SectionUiState<List<String>> = SectionUiState.Loading,
)