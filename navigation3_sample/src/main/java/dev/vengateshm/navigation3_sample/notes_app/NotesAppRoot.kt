package dev.vengateshm.navigation3_sample.notes_app

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.savedstate.serialization.SavedStateConfiguration
import dev.vengateshm.navigation3_sample.notes_app.auth.AuthNavDisplay
import dev.vengateshm.navigation3_sample.notes_app.notes.NotesNavDisplay
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic

@Composable
fun NotesAppRoot(modifier: Modifier = Modifier) {
  val rootBackstack = rememberNavBackStack(
    configuration = SavedStateConfiguration {
      serializersModule = SerializersModule {
        polymorphic(NavKey::class) {
          subclass(NotesAppRoute.Auth::class, NotesAppRoute.Auth.serializer())
          subclass(NotesAppRoute.Notes::class, NotesAppRoute.Notes.serializer())
        }
      }
    },
    NotesAppRoute.Auth,
  )

  NavDisplay(
    modifier = modifier,
    backStack = rootBackstack,
    entryDecorators = listOf(
      rememberSaveableStateHolderNavEntryDecorator(),
      rememberViewModelStoreNavEntryDecorator(),
    ),
    entryProvider = entryProvider {
      entry<NotesAppRoute.Auth> {
        AuthNavDisplay(
          onLogin = {
            rootBackstack.remove(NotesAppRoute.Auth)
            rootBackstack.add(NotesAppRoute.Notes)
          },
        )
      }
      entry<NotesAppRoute.Notes> {
        NotesNavDisplay()
      }
    },
  )
}