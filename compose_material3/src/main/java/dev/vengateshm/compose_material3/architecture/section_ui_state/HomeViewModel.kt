package dev.vengateshm.compose_material3.architecture.section_ui_state

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
  private val _items = MutableStateFlow<SectionUiState<List<String>>>(SectionUiState.Loading)
  private val _topRated = MutableStateFlow<SectionUiState<List<String>>>(SectionUiState.Loading)
  private val _notes = MutableStateFlow<SectionUiState<List<String>>>(SectionUiState.Loading)
  private val _cards = MutableStateFlow<SectionUiState<List<String>>>(SectionUiState.Loading)

  val state: StateFlow<HomeScreenState> = combine(
    _items,
    _topRated,
    _notes,
    _cards,
  ) { items, topRated, notes, cards ->
    HomeScreenState(
      items = items,
      topRated = topRated,
      notes = notes,
      cards = cards,
    )
  }.stateIn(
    viewModelScope,
    SharingStarted.WhileSubscribed(5000),
    HomeScreenState(),
  )

  init {
    loadItems()
    loadTopRated()
    loadCards()
    loadNotes()
  }

  fun loadItems() {
    viewModelScope.launch {
      _items.value = SectionUiState.Loading
      try {
        delay(1500)
        _items.value = SectionUiState.Success(List(4) { "Item $it" })
      } catch (e: Exception) {
        _items.value =
          SectionUiState.Error("Items failed")
      }
    }
  }

  fun loadTopRated() {
    viewModelScope.launch {
      _topRated.value = SectionUiState.Loading
      try {
        delay(2000)
        _topRated.value = SectionUiState.Success(List(4) { "Top Rated $it" })
      } catch (e: Exception) {
        _topRated.value =
          SectionUiState.Error("Top Rated failed")
      }
    }
  }

  fun loadNotes() {
    viewModelScope.launch {
      _notes.value = SectionUiState.Loading
      try {
        delay(2500)
        _notes.value = SectionUiState.Success(List(4) { "Note $it" })
      } catch (e: Exception) {
        _notes.value =
          SectionUiState.Error("Notes failed")
      }
    }
  }

  fun loadCards() {
    viewModelScope.launch {
      _cards.value = SectionUiState.Loading
      try {
        delay(3000)
        _cards.value = SectionUiState.Success(List(4) { "Card $it" })
      } catch (e: Exception) {
        _cards.value =
          SectionUiState.Error("Cards failed")
      }
    }
  }
}