package dev.vengateshm.navigation3_sample.notes_app.notes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dev.vengateshm.navigation3_sample.notes_app.NotesAppRoute
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class NoteDetailViewModel(
  private val noteId: Int,
) : ViewModel() {
  private val _noteState = MutableStateFlow(
    sampleNotes.first { it.id == noteId },
  )
  val noteState = _noteState.asStateFlow()

  class Factory(
    private val key: NotesAppRoute.Notes.NoteDetail,
  ) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
      return NoteDetailViewModel(key.id) as T
    }
  }

  override fun onCleared() {
    println("NoteViewModel onCleared() called")
    super.onCleared()
  }
}