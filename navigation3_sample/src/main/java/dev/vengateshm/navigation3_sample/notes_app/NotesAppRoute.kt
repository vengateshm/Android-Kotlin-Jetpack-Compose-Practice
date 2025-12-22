package dev.vengateshm.navigation3_sample.notes_app

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface NotesAppRoute : NavKey {
  @Serializable
  data object Auth : NotesAppRoute, NavKey {
    @Serializable
    data object Login : NotesAppRoute, NavKey

    @Serializable
    data object Register : NotesAppRoute, NavKey
  }

  @Serializable
  data object Notes : NotesAppRoute, NavKey {
    @Serializable
    data object NoteList : NotesAppRoute, NavKey

    @Serializable
    data class NoteDetail(val id: Int) : NotesAppRoute, NavKey
  }
}