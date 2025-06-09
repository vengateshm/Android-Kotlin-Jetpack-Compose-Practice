package dev.vengateshm.compose_material3.ui_concepts.bottom_navigation_with_nav_drawer

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun BottomNavWithNavDrawer(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(
        modifier = modifier
            .fillMaxSize()
            .safeContentPadding(),
        navController = navController,
        startDestination = AppDestination.AuthDestination,
    ) {
        composable<AppDestination.AuthDestination> {
            AuthScreen(
                onNavigateToMain = {
                    navController.navigate(AppDestination.MainDestination) {
                        popUpTo(0) { // 0 - ID of root graph
                            inclusive = true
                        }
                    }
                },
            )
        }
        composable<AppDestination.MainDestination> {
            MainScreen(
                onLogout = {
                    navController.navigate(AppDestination.AuthDestination) {
                        popUpTo(AppDestination.MainDestination) {
                            inclusive = true
                        }
                    }
                },
                onNavigateToSettings = {
                    navController.navigate(AppDestination.SettingsDestination)
                },
            )
        }
        composable<AppDestination.SettingsDestination> {
            SettingsScreen(
                onCloseSettings = {
                    navController.navigateUp()
                },
            )
        }
    }
}