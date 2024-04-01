package dev.vengateshm.android_kotlin_compose_practice.localization_app

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun LocalizationApp() {
    val navController = rememberNavController()
    val navBackStackEntryState by navController.currentBackStackEntryAsState()

    val items =
        remember {
            listOf(
                BottomNavItemContent(
                    name = Screen.List.name,
                    icon = Icons.Default.List,
                ),
                BottomNavItemContent(
                    name = Screen.Settings.name,
                    icon = Icons.Default.Settings,
                ),
            )
        }

    Scaffold(
        bottomBar = {
            BottomNavigation {
                items.forEach { item ->
                    val isSelected =
                        item.name.lowercase() ==
                            navBackStackEntryState?.destination?.route
                    BottomNavigationItem(
                        selected = isSelected,
                        onClick = {
                            navController.navigate(item.name.lowercase()) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = {
                            Icon(
                                imageVector = item.icon,
                                contentDescription = "${item.name} icon",
                            )
                        },
                    )
                }
            }
        },
    ) { paddingValues ->
        NavHost(
            modifier = Modifier.padding(paddingValues),
            navController = navController,
            startDestination = Screen.List.name,
        ) {
            composable(Screen.List.name) {
                ListScreen()
            }
            composable(Screen.Settings.name) {
                SettingsScreen()
            }
        }
    }
}

sealed class Screen(val name: String) {
    data object List : Screen("list")

    data object Settings : Screen("settings")
}

data class BottomNavItemContent(val name: String, val icon: ImageVector)
