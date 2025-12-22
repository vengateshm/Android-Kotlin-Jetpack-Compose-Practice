package dev.vengateshm.navigation3_sample.nested_navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import dev.vengateshm.navigation3_sample.destinations.AppDestination
import dev.vengateshm.navigation3_sample.screens.ScreenWithAText
import dev.vengateshm.navigation3_sample.screens.ScreenWithATextAndBackButton
import dev.vengateshm.navigation3_sample.screens.ScreenWithTextAndButton

@Composable
fun NestedNavigationAppRoot(modifier: Modifier = Modifier) {
  val backStack = rememberNavBackStack(AppDestination.WelcomeDestination)

  NavDisplay(
    modifier = modifier.fillMaxSize(),
    backStack = backStack,
    onBack = { backStack.removeLastOrNull() },
    entryProvider = entryProvider {
      entry<AppDestination.WelcomeDestination> {
        ScreenWithTextAndButton(
          text = "Welcome!",
          buttonText = "Go",
          onButtonClick = {
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
        ScreenWithATextAndBackButton(
          title = "Settings",
          text = "Settings",
          onBackClick = {
            backStack.removeLastOrNull()
          },
        )
      }
    },
  )
}