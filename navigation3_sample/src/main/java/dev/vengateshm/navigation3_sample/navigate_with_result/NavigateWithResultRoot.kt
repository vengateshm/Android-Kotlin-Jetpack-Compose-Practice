package dev.vengateshm.navigation3_sample.navigate_with_result

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import dev.vengateshm.navigation3_sample.destinations.AppDestination
import dev.vengateshm.navigation3_sample.screens.ScreenWithAButton
import dev.vengateshm.navigation3_sample.screens.ScreenWithInputFieldAndButton

@Composable
fun NavigateWithResultRoot(modifier: Modifier = Modifier) {
  val backStack = rememberNavBackStack(AppDestination.NavigateWithResultScreen1)
  val resultStore = rememberResultStore()

  NavDisplay(
    backStack = backStack,
    entryProvider = entryProvider {
      entry<AppDestination.NavigateWithResultScreen1> {
        val result = resultStore.getResult<String>("main_setting") ?: "Default"
        ScreenWithAButton(
          text = "Current setting: $result",
          onClick = {
            backStack.add(AppDestination.SettingsDestination)
          },
        )
      }
      entry<AppDestination.SettingsDestination> {
        ScreenWithInputFieldAndButton(
          buttonText = "Save",
          onClick = {
            resultStore.setResult("main_setting", it)
            backStack.removeLastOrNull()
          },
        )
      }
    },
    transitionSpec = {
      slideInHorizontally { it } + fadeIn() togetherWith
          slideOutHorizontally { -it } + fadeOut()
    },
    popTransitionSpec = {
      slideInHorizontally { -it } + fadeIn() togetherWith
          slideOutHorizontally { it } + fadeOut()
    },
    predictivePopTransitionSpec = {
      slideInHorizontally { -it } + fadeIn() togetherWith
          slideOutHorizontally { it } + fadeOut()
    },
  )
}