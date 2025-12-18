package dev.vengateshm.navigation3_sample.nested_navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import dev.vengateshm.navigation3_sample.destinations.AppDestination
import dev.vengateshm.navigation3_sample.list_detail_screens.Nav3DetailScreen
import dev.vengateshm.navigation3_sample.list_detail_screens.Nav3ListScreen

@Composable
fun MainScreen(
  modifier: Modifier = Modifier,
  onNavigateToSettings: () -> Unit,
) {
  val navBackStack = rememberNavBackStack(AppDestination.Nav3List)

  NavDisplay(
    modifier = Modifier.fillMaxSize(),
    backStack = navBackStack,
    onBack = { navBackStack.removeLastOrNull() },
    entryProvider = entryProvider {
      entry<AppDestination.Nav3List> {
        Nav3ListScreen(
          onNavigateToDetail = {
            navBackStack.add(AppDestination.Nav3Detail(it))
          },
          onNavigateToSettings = onNavigateToSettings,
        )
      }
      entry<AppDestination.Nav3Detail> {
        Nav3DetailScreen(
          data = it.data,
        )
      }
    },
  )
}