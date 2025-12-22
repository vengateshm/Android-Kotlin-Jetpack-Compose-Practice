package dev.vengateshm.navigation3_sample.list_detail_strategy

import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.navigation3.ListDetailSceneStrategy
import androidx.compose.material3.adaptive.navigation3.rememberListDetailSceneStrategy
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import dev.vengateshm.navigation3_sample.destinations.AppDestination
import dev.vengateshm.navigation3_sample.screens.ListScreen
import dev.vengateshm.navigation3_sample.screens.ScreenWithAText

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun ListDetailAppRoot(modifier: Modifier = Modifier) {
  val backStack = rememberNavBackStack(AppDestination.ListDestination)
  val listDetailSceneStrategy = rememberListDetailSceneStrategy<NavKey>()

  NavDisplay(
    backStack = backStack,
    sceneStrategy = listDetailSceneStrategy,
    entryProvider = entryProvider {
      entry<AppDestination.ListDestination>(
        metadata = ListDetailSceneStrategy.listPane(
          detailPlaceholder = {
            ScreenWithAText(
              text = "Select an item",
            )
          },
        ),
      ) {
        ListScreen(
          onItemClick = {
            backStack.add(AppDestination.DetailDestination(it))
          },
        )
      }
      entry<AppDestination.DetailDestination>(
        metadata = ListDetailSceneStrategy.detailPane(),
      ) {
        ScreenWithAText(
          text = it.data
        )
      }
    },
  )
}