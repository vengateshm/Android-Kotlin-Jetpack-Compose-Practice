package dev.vengateshm.navigation3_sample.list_detail_strategy

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.navigation3.ListDetailSceneStrategy
import androidx.compose.material3.adaptive.navigation3.rememberListDetailSceneStrategy
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import dev.vengateshm.navigation3_sample.destinations.AppDestination
import dev.vengateshm.navigation3_sample.list_detail_screens.Nav3DetailScreen
import dev.vengateshm.navigation3_sample.list_detail_screens.Nav3ListScreen

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun ListDetailAppRoot(modifier: Modifier = Modifier) {
  val backStack = rememberNavBackStack(AppDestination.Nav3List)
  val listDetailSceneStrategy = rememberListDetailSceneStrategy<NavKey>()

  NavDisplay(
    backStack = backStack,
    sceneStrategy = listDetailSceneStrategy,
    entryProvider = entryProvider {
      entry<AppDestination.Nav3List>(
        metadata = ListDetailSceneStrategy.listPane(
          detailPlaceholder = {
            Column(
              modifier = Modifier.fillMaxSize(),
              horizontalAlignment = Alignment.CenterHorizontally,
              verticalArrangement = Arrangement.Center,
            ) {
              Text(text = "Select an item")
            }
          },
        ),
      ) {
        Nav3ListScreen(
          onNavigateToDetail = {
            backStack.add(AppDestination.Nav3Detail(it))
          },
        )
      }
      entry<AppDestination.Nav3Detail>(
        metadata = ListDetailSceneStrategy.detailPane(),
      ) {
        Nav3DetailScreen(
          data = it.data,
        )
      }
    },
  )
}