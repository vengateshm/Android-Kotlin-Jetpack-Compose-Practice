package dev.vengateshm.navigation3_sample.bottom_sheet

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import dev.vengateshm.navigation3_sample.destinations.AppDestination
import dev.vengateshm.navigation3_sample.screens.ListScreen

@Composable
fun BottomSheetNavAppRoot(modifier: Modifier = Modifier) {
  val navBackStack = rememberNavBackStack(AppDestination.ListDestination)
  val bottomSheetSceneStrategy = remember { BottomSheetSceneStrategy<NavKey>() }

  NavDisplay(
    modifier = Modifier.fillMaxSize(),
    backStack = navBackStack,
    sceneStrategy = bottomSheetSceneStrategy,
    onBack = { navBackStack.removeLastOrNull() },
    entryProvider = entryProvider {
      entry<AppDestination.ListDestination> {
        ListScreen(
          onItemClick = {
            navBackStack.add(AppDestination.BottomSheetDestination(it))
          },
        )
      }
      entry<AppDestination.BottomSheetDestination> {
        CustomBottomSheet(
          data = it.data,
          onDismissed = {
            navBackStack.removeLastOrNull()
          },
        )
      }
    },
  )
}