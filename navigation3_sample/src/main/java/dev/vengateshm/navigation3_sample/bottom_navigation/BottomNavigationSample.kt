package dev.vengateshm.navigation3_sample.bottom_navigation

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entry
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import dev.vengateshm.navigation3_sample.bottom_navigation.AppScreen.Home
import kotlinx.serialization.Serializable

interface BottomNavItem {
    val icon: ImageVector
    val title: String
}

@Serializable
sealed class AppScreen : NavKey {
    @Serializable
    data object Home : AppScreen(), BottomNavItem {
        override val icon: ImageVector
            get() = Icons.Filled.Home
        override val title: String
            get() = "Home"
    }

    @Serializable
    data object Settings : AppScreen(), BottomNavItem {
        override val icon: ImageVector
            get() = Icons.Filled.Settings
        override val title: String
            get() = "Settings"
    }

    @Serializable
    data object Detail1 : AppScreen()

    @Serializable
    data object Detail2 : AppScreen()
}

class AppNavBackStack<T : NavKey>(private val startKey: T) {
    var bottomTabKey by mutableStateOf(startKey)
        private set

    val backStack = mutableStateListOf<T>(startKey)

    // Key - Tab, Value - Tab screen and other screens called from tab
    private var bottomTabBackStacks: HashMap<T, SnapshotStateList<T>> =
        hashMapOf(startKey to mutableStateListOf(startKey))

    fun switchBottomTab(key: T) {
        if (bottomTabBackStacks[key] == null) {
            println("Initializing stack for tab: $key")
            bottomTabBackStacks[key] = mutableStateListOf(key)
        }
        println("Switching to bottom tab: $key")
        bottomTabKey = key
        updateBackStack()
    }

    fun add(key: T) {
        println("Adding screen $key to tab $bottomTabKey")
        bottomTabBackStacks[bottomTabKey]?.add(key)
        updateBackStack()
    }

    fun removeLast() {
        val currentStack = bottomTabBackStacks[bottomTabKey] ?: return
        if (currentStack.size > 1) {
            val removed = currentStack.removeLastOrNull()
            println("Removed last screen $removed from tab $bottomTabKey")
        } else if (bottomTabKey != startKey) {
            println("Current tab $bottomTabKey has only one screen. Switching back to start tab: $startKey")
            bottomTabKey = startKey
        } else {
            println("Cannot remove last screen from start tab $startKey")
        }
        updateBackStack()
    }

    private fun updateBackStack() {
        backStack.clear()
        val currentTabStack = bottomTabBackStacks[bottomTabKey] ?: emptyList()

        if (bottomTabKey == startKey) {
            backStack.addAll(currentTabStack)
            println("Updated backStack (on start tab): $backStack")
        } else {
            val startTabStack = bottomTabBackStacks[startKey] ?: emptyList()
            backStack.addAll(startTabStack + currentTabStack)
            println("Updated backStack (on non-start tab): $backStack")
        }
    }

    fun replaceStack(vararg keys: T) {
        println("Replacing stack on tab $bottomTabKey with: ${keys.toList()}")
        bottomTabBackStacks[bottomTabKey] = mutableStateListOf(*keys)
        updateBackStack()
    }
}

@Composable
fun BottomNavSample(modifier: Modifier = Modifier) {
    val bottomNavItems = listOf(Home, AppScreen.Settings)
    val backStack = remember { AppNavBackStack<NavKey>(Home) }
    Scaffold(
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
                entry<Home> {
                    HomeScreen(
                        onNavigateToDetail1 = {
                            backStack.add(AppScreen.Detail1)
                        },
                        modifier = screenModifier,
                    )
                }
                entry<AppScreen.Settings> {
                    SettingsScreen(
                        onNavigateToDetail2 = {
                            backStack.add(AppScreen.Detail2)
                        },
                        modifier = screenModifier,
                    )
                }
                entry<AppScreen.Detail1> {
                    DetailScreen1(
                        modifier = screenModifier,
                        onBackClick = {
                            backStack.removeLast()
                        },
                    )
                }
                entry<AppScreen.Detail2> {
                    DetailScreen2(
                        modifier = screenModifier,
                        onBackClick = {
                            backStack.removeLast()
                        },
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

@Composable
fun HomeScreen(
    onNavigateToDetail1: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Button(onClick = onNavigateToDetail1) {
            Text("Go to Detail 1")
        }
    }
}

@Composable
fun SettingsScreen(
    onNavigateToDetail2: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Button(onClick = onNavigateToDetail2) {
            Text("Go to Detail 2")
        }
    }
}

@Composable
fun DetailScreen1(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
) {
    Column(
        modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text("Detail Screen 1")
        Button(onClick = onBackClick) {
            Text("Go Back")
        }
    }
}

@Composable
fun DetailScreen2(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
) {
    Column(
        modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text("Detail Screen 2")
        Button(onClick = onBackClick) {
            Text("Go Back")
        }
    }
}

@Preview
@Composable
private fun BottomNavSamplePreview() {
    BottomNavSample()
}