package dev.vengateshm.navigation3_sample.notes_app.notes

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.savedstate.serialization.SavedStateConfiguration
import dev.vengateshm.navigation3_sample.notes_app.NotesAppRoute
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic

@Composable
fun NotesNavDisplay(
  modifier: Modifier = Modifier,
) {
  val notesBackstack = rememberNavBackStack(
    configuration = SavedStateConfiguration {
      serializersModule = SerializersModule {
        polymorphic(NavKey::class) {
          subclass(
            NotesAppRoute.Notes.NoteList::class,
            NotesAppRoute.Notes.NoteList.serializer(),
          )
          subclass(
            NotesAppRoute.Notes.NoteDetail::class,
            NotesAppRoute.Notes.NoteDetail.serializer(),
          )
        }
      }
    },
    NotesAppRoute.Notes.NoteList,
  )

  NavDisplay(
    modifier = modifier,
    backStack = notesBackstack,
    entryDecorators = listOf(
      rememberSaveableStateHolderNavEntryDecorator(),
      rememberViewModelStoreNavEntryDecorator(),
    ),
    entryProvider = entryProvider {
      entry<NotesAppRoute.Notes.NoteList> {
        NoteListScreen(
          onNoteClick = {
            notesBackstack.add(NotesAppRoute.Notes.NoteDetail(id = it))
          },
        )
      }
      entry<NotesAppRoute.Notes.NoteDetail> {
        NoteDetailsScreen(viewModel = viewModel(factory = NoteDetailViewModel.Factory(key = it)))
      }
    },
  )
}