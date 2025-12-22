package dev.vengateshm.navigation3_sample.nav3basic

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import dev.vengateshm.navigation3_sample.destinations.AppDestination
import dev.vengateshm.navigation3_sample.screens.ScreenWithAButton
import dev.vengateshm.navigation3_sample.screens.ScreenWithAText

@Composable
fun Nav3Basic(modifier: Modifier = Modifier) {
//    val backStack = remember { mutableStateListOf<Screen>(Screen.Home) }
//    val backStack = rememberNavBackStack<Screen>(Screen.Home)

  val viewModel = viewModel<Nav3BasicViewModel>()
  val backStack = viewModel.backStack

  NavDisplay(
    backStack = backStack,
    onBack = {
      backStack.removeLastOrNull()
    },
    entryProvider = entryProvider {
      homeEntry(backStack)
      entry<AppDestination.DetailDestination>(
        metadata = NavDisplay.transitionSpec {
          slideInHorizontally { it } togetherWith slideOutHorizontally { -it }
        } + NavDisplay.popTransitionSpec {
          slideInVertically { it } togetherWith slideOutVertically { it }
        } + NavDisplay.predictivePopTransitionSpec {
          slideInVertically { it } togetherWith slideOutVertically { it }
        },
      ) { key ->
        ScreenWithAText(
          text = "Details Screen ${key.data}",
        )
      }
    },
  )
}

private fun EntryProviderScope<AppDestination>.homeEntry(backStack: SnapshotStateList<AppDestination>) {
  entry<AppDestination.HomeDestination> {
    ScreenWithAButton(
      text = "Go To Details",
      onClick = {
        backStack.add(AppDestination.DetailDestination("123"))
      },
    )
  }
}