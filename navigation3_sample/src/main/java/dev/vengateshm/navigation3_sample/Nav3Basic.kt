package dev.vengateshm.navigation3_sample

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay

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
      entry<Screen.Details>(
        metadata = NavDisplay.transitionSpec {
          slideInHorizontally { it } togetherWith slideOutHorizontally { -it }
        } + NavDisplay.popTransitionSpec {
          slideInVertically { it } togetherWith slideOutVertically { it }
        } + NavDisplay.predictivePopTransitionSpec {
          slideInVertically { it } togetherWith slideOutVertically { it }
        },
      ) { key ->
        Box(
          modifier = Modifier.fillMaxSize(),
          contentAlignment = Alignment.Center,
        ) {
          Text(text = "Details Screen ${key.id}")
        }
      }
    },
  )
}

class Nav3BasicViewModel : ViewModel() {
  val backStack = mutableStateListOf<Screen>(Screen.Home)
}

private fun EntryProviderScope<Screen>.homeEntry(backStack: SnapshotStateList<Screen>) {
  entry<Screen.Home> {
    Box(
      modifier = Modifier.fillMaxSize(),
      contentAlignment = Alignment.Center,
    ) {
      Button(
        onClick = {
          backStack.add(Screen.Details("123"))
        },
      ) {
        Text(text = "Go To Details")
      }
    }
  }
}