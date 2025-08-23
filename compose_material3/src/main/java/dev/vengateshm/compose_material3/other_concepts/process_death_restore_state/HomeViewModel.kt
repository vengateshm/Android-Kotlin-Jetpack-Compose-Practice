package dev.vengateshm.compose_material3.other_concepts.process_death_restore_state

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class HomeViewModel(
  private val savedStateHandle: SavedStateHandle,
) : ViewModel() {
  var retainedFavoriteNumber by mutableIntStateOf(savedStateHandle["number"] ?: 0)
    private set

  var favoriteNumber by mutableIntStateOf(0)

  fun updateNumber(value: Int) {
    favoriteNumber = value
  }

  fun updateRetainedNumber(value: Int) {
    retainedFavoriteNumber = value
    savedStateHandle["number"] = value
  }
}