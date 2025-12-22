package dev.vengateshm.navigation3_sample.nested_navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import dev.vengateshm.navigation3_sample.destinations.AppDestination
import dev.vengateshm.navigation3_sample.screens.ScreenWithAButton
import dev.vengateshm.navigation3_sample.screens.ScreenWithTextAndButton

@Composable
fun NestedNavigationWithBottomNavRoot(modifier: Modifier = Modifier) {
  val backStack = rememberNavBackStack(AppDestination.WelcomeDestination)

  NavDisplay(
    backStack = backStack,
    onBack = {
      backStack.removeLastOrNull()
    },
    entryDecorators = listOf(
      rememberSaveableStateHolderNavEntryDecorator(),
//      rememberViewModelStoreNavEntryDecorator(), // In nested graph scenario it ties viewmodel to the NavDisplay
    ),
    entryProvider = entryProvider {
      entry<AppDestination.WelcomeDestination> {
        ScreenWithTextAndButton(
          text = "Welcome!",
          buttonText = "Sign in",
          onButtonClick = {
            backStack.add(AppDestination.MainDestination)
//            backStack.removeFirstOrNull()
          },
        )
      }
      entry<AppDestination.SettingsDestination> {
        ScreenWithAButton(
          buttonText = "Navigate back",
          onClick = {
            backStack.removeLastOrNull()
          },
        )
      }
      entry<AppDestination.MainDestination> {
        NestedGraphWithBottomNav(
          onSettingClick = {
            backStack.add(AppDestination.SettingsDestination)
          },
        )
      }
    },
  )
}