package dev.vengateshm.navigation3_sample.nested_navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.saveable.rememberSaveableStateHolder
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import dev.vengateshm.navigation3_sample.destinations.BottomNavBarScreen
import dev.vengateshm.navigation3_sample.destinations.BottomNavBarScreenSaver
import dev.vengateshm.navigation3_sample.destinations.bottomNavBarItems
import dev.vengateshm.navigation3_sample.screens.ScreenWithAText
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NestedGraphWithBottomNav(
  onSettingClick: () -> Unit,
  modifier: Modifier = Modifier,
) {
  val backStack = rememberNavBackStack(BottomNavBarScreen.Home)
  var currentScreen: BottomNavBarScreen by rememberSaveable(stateSaver = BottomNavBarScreenSaver) {
    mutableStateOf(BottomNavBarScreen.Home)
  }

  val stateHolder = rememberSaveableStateHolder()

  Scaffold(
    modifier = modifier.fillMaxSize(),
    topBar = {
      TopAppBar(
        title = {
          Text(text = "Main")
        },
        actions = {
          IconButton(onClick = onSettingClick) {
            Icon(
              imageVector = Icons.Outlined.Settings,
              contentDescription = "Settings",
            )
          }
        },
      )
    },
    bottomBar = {
      NavigationBar {
        bottomNavBarItems.forEach { destination ->
          NavigationBarItem(
            selected = destination == currentScreen,
            icon = {
              Icon(
                painterResource(destination.icon),
                contentDescription = "${destination.title} icon",
              )
            },
            onClick = {
              if (backStack.lastOrNull() != destination) {
                if (backStack.lastOrNull() in bottomNavBarItems) {
                  backStack.removeAt(backStack.lastIndex)
                }
                backStack.add(destination)
                currentScreen = destination
              }
            },
          )
        }
      }
    },
  ) {
    NavDisplay(
      backStack = backStack,
      onBack = {
        backStack.removeLastOrNull()
      },
      entryDecorators = listOf(
        rememberSaveableStateHolderNavEntryDecorator(),
//        rememberViewModelStoreNavEntryDecorator(), // Ties viewmodel to each nav entry
      ),
      entryProvider = entryProvider {
        entry<BottomNavBarScreen.Home> {
//          var number by remember { mutableIntStateOf(0) }
//
//          LaunchedEffect(Unit) {
//            while (true) {
//              delay(1000L)
//              number++
//            }
//          }
//
//          ScreenWithAText(text = "Home ($number)")

          stateHolder.SaveableStateProvider(key = it.title) {
            val viewModel = viewModel<HomeViewModel>()
            var number by rememberSaveable { mutableIntStateOf(0) }
            val number2 = viewModel.num2
            LaunchedEffect(Unit) {
              while (true) {
                delay(1000L)
                number++
                viewModel.incrementNumber2()
              }
            }

            ScreenWithAText(text = "Home ($number) - ($number2)")
          }
        }
        entry<BottomNavBarScreen.Me> {
          ScreenWithAText(text = "Me")
        }
      },
    )
  }
}