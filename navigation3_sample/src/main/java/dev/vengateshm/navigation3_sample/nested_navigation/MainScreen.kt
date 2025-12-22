package dev.vengateshm.navigation3_sample.nested_navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import dev.vengateshm.navigation3_sample.destinations.AppDestination
import dev.vengateshm.navigation3_sample.screens.ListScreen
import dev.vengateshm.navigation3_sample.screens.ScreenWithAText

@Composable
fun MainScreen(
  modifier: Modifier = Modifier,
  onNavigateToSettings: () -> Unit,
) {
  val navBackStack = rememberNavBackStack(AppDestination.ListDestination)

  NavDisplay(
    modifier = modifier.fillMaxSize(),
    backStack = navBackStack,
    onBack = { navBackStack.removeLastOrNull() },
    entryProvider = entryProvider {
      entry<AppDestination.ListDestination> {
        Column(
          modifier = Modifier.fillMaxSize(),
          horizontalAlignment = Alignment.CenterHorizontally,
          verticalArrangement = Arrangement.Center,
        ) {
          Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
              onNavigateToSettings()
            },
          ) {
            Text("Settings")
          }

          Spacer(modifier = Modifier.height(48.dp))

          ListScreen(
            onItemClick = {
              navBackStack.add(AppDestination.DetailDestination(it))
            },
          )
        }
      }
      entry<AppDestination.DetailDestination> {
        ScreenWithAText(
          text = it.data,
        )
      }
    },
  )
}