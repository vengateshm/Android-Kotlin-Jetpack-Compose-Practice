package dev.vengateshm.navigation3_sample.navigate_with_result

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import dev.vengateshm.navigation3_sample.destinations.AppDestination

@Composable
fun NavigateWithResultRoot(modifier: Modifier = Modifier) {
  val backStack = rememberNavBackStack(AppDestination.NavigateWithResultScreen1)
  val resultStore = rememberResultStore()

  NavDisplay(
    backStack = backStack,
    entryProvider = entryProvider {
      entry<AppDestination.NavigateWithResultScreen1> {
        val result = resultStore.getResult<String>("main_setting") ?: "Default"
        Box(
          modifier = Modifier.fillMaxSize(),
          contentAlignment = Alignment.Center,
        ) {
          Button(
            onClick = {
              backStack.add(AppDestination.ChangeSettingsDestination)
            },
          ) {
            Text("Current setting: $result")
          }
        }
      }
      entry<AppDestination.ChangeSettingsDestination> {
        ChangeSettingsScreen(
          resultStore = resultStore,
          onSave = {
            backStack.removeLastOrNull()
          },
        )
      }
    },
  )
}