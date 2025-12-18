package dev.vengateshm.navigation3_sample.nested_navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import dev.vengateshm.navigation3_sample.destinations.AppDestination

@Composable
fun NestedNavigationAppRoot(modifier: Modifier = Modifier) {
  val backStack = rememberNavBackStack(AppDestination.WelcomeDestination)

  NavDisplay(
    modifier = Modifier.fillMaxSize(),
    backStack = backStack,
    onBack = { backStack.removeLastOrNull() },
    entryProvider = entryProvider {
      entry<AppDestination.WelcomeDestination> {
        WelcomeScreen(
          onNavigateToHome = {
            backStack.add(AppDestination.MainDestination)
            backStack.removeFirstOrNull()
          },
        )
      }
      entry<AppDestination.MainDestination> {
        MainScreen(
          onNavigateToSettings = {
            backStack.add(AppDestination.SettingsDestination)
          },
        )
      }
      entry<AppDestination.SettingsDestination> {
        SettingsScreen(
          onNavigateBack = {
            backStack.removeLastOrNull()
          },
        )
      }
    },
  )
}