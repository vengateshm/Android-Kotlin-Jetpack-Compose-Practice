package dev.vengateshm.compose_material3.architecture.mvvm_mvi_combined

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class CombineFlowsViewModel : ViewModel() {
  private val _items = MutableStateFlow<List<String>>(emptyList())
  private val _isLoading = MutableStateFlow(false)
  private val _errorMessage = MutableStateFlow<String?>(null)

  val state: StateFlow<CombinedFlowsScreenState> = combine(
    flow = _items,
    flow2 = _isLoading,
    flow3 = _errorMessage,
  ) { items, isLoading, errorMessage ->

    CombinedFlowsScreenState(
      items = items,
      isLoading = isLoading,
      errorMessage = errorMessage,
    )
  }.stateIn(
    scope = viewModelScope,
    started = SharingStarted.WhileSubscribed(5000),
    initialValue = CombinedFlowsScreenState(),
  )

  fun loadData() {
    viewModelScope.launch {
      try {
        _isLoading.value = true
        _errorMessage.value = null
        delay(2000) // simulate network
        val result = List(20) {
          "Item #$it"
        }
        _items.value = result
      } catch (e: Exception) {
        _errorMessage.value = "Failed to load data"
      } finally {
        _isLoading.value = false
      }
    }
  }
}