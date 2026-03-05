package dev.vengateshm.compose_material3.architecture.mvvm_mvi_combined

data class CombinedFlowsScreenState(
  val items: List<String> = emptyList(),
  val isLoading: Boolean = false,
  val errorMessage: String? = null,
)