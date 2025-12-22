package dev.vengateshm.navigation3_sample.notes_app.auth

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
fun AuthNavDisplay(
  onLogin: () -> Unit,
  modifier: Modifier = Modifier,
) {
  val authBackstack = rememberNavBackStack(
    configuration = SavedStateConfiguration {
      serializersModule = SerializersModule {
        polymorphic(NavKey::class) {
          subclass(NotesAppRoute.Auth.Login::class, NotesAppRoute.Auth.Login.serializer())
          subclass(NotesAppRoute.Auth.Register::class, NotesAppRoute.Auth.Register.serializer())
        }
      }
    },
    NotesAppRoute.Auth.Login,
  )

  val sharedAuthViewModel = viewModel { SharedAuthViewModel() }

  NavDisplay(
    modifier = modifier,
    backStack = authBackstack,
    entryDecorators = listOf(
      rememberSaveableStateHolderNavEntryDecorator(),
      rememberViewModelStoreNavEntryDecorator(),
    ),
    entryProvider = entryProvider {
      entry<NotesAppRoute.Auth.Login> {
        LoginScreen(
          viewModel = viewModel { LoginViewModel() },
          sharedAuthViewModel = sharedAuthViewModel,
          onLogin = onLogin,
          onRegister = {
            authBackstack.add(NotesAppRoute.Auth.Register)
          },
        )
      }
      entry<NotesAppRoute.Auth.Register> {
        RegisterScreen(
          viewModel = viewModel { RegisterViewModel() },
          sharedAuthViewModel = sharedAuthViewModel,
        )
      }
    },
  )
}