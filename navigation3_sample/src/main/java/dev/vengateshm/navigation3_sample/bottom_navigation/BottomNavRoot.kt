package dev.vengateshm.navigation3_sample.bottom_navigation

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import dev.vengateshm.navigation3_sample.destinations.AppDestination
import dev.vengateshm.navigation3_sample.screens.ScreenWithAButton
import dev.vengateshm.navigation3_sample.screens.ScreenWithTextAndButton

@Composable
fun BottomNavRoot(modifier: Modifier = Modifier) {
  val bottomNavItems = listOf(AppDestination.HomeDestination, AppDestination.ProfileDestination)
  val backStack = remember { AppNavBackStack<NavKey>(AppDestination.HomeDestination) }

  Scaffold(
    modifier = modifier,
    bottomBar = {
      NavigationBar {
        bottomNavItems.forEach { item ->
          NavigationBarItem(
            selected = backStack.bottomTabKey == item,
            onClick = {
              backStack.switchBottomTab(item)
            },
            icon = { Icon(item.icon, contentDescription = item.title) },
            label = { Text(item.title) },
          )
        }
      }
    },
  ) { innerPadding ->
    val screenModifier = Modifier
      .fillMaxSize()
      .padding(innerPadding)
    NavDisplay(
      backStack = backStack.backStack,
      onBack = { backStack.removeLast() },
      entryProvider = entryProvider {
        entry<AppDestination.HomeDestination> {
          ScreenWithAButton(
            buttonText = "Go to Detail 1",
            onClick = {
              backStack.add(AppDestination.DetailDestination2)
            },
            modifier = screenModifier,
          )
        }
        entry<AppDestination.ProfileDestination> {
          ScreenWithAButton(
            buttonText = "Go to Detail 2",
            onClick = {
              backStack.add(AppDestination.DetailDestination3)
            },
            modifier = screenModifier,
          )
        }
        entry<AppDestination.DetailDestination2> {
          ScreenWithTextAndButton(
            text = "Detail Screen 1",
            buttonText = "Go Back",
            onButtonClick = {
              backStack.removeLast()
            },
            modifier = screenModifier,
          )
        }
        entry<AppDestination.DetailDestination3> {
          ScreenWithTextAndButton(
            text = "Detail Screen 2",
            buttonText = "Go Back",
            onButtonClick = {
              backStack.removeLast()
            },
            modifier = screenModifier,
          )
        }
      },
      transitionSpec = {
        // Slide in from right when navigating forward
        slideInHorizontally(initialOffsetX = { it }) togetherWith
            slideOutHorizontally(targetOffsetX = { -it })
      },
      popTransitionSpec = {
        // Slide in from left when navigating back
        slideInHorizontally(initialOffsetX = { -it }) togetherWith
            slideOutHorizontally(targetOffsetX = { it })
      },
      predictivePopTransitionSpec = {
        // Slide in from left when navigating back
        slideInHorizontally(initialOffsetX = { -it }) togetherWith
            slideOutHorizontally(targetOffsetX = { it })
      },
    )
  }
}

@Preview
@Composable
private fun BottomNavRootPreview() {
  BottomNavRoot()
}