package dev.vengateshm.compose_material3.api_compose.navigation.navigation_without_one_time_event

import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.koin.compose.koinInject

@Composable
fun Navigator1Sample(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val navigator1 = koinInject<Navigator1>()
    DisposableEffect(navController) {
        navigator1.setNavHostController(navController)
        onDispose {
            navigator1.clear()
        }
    }
    NavHost(
        navController = navController,
        startDestination = Dest1.Profile,
        modifier = Modifier.safeContentPadding(),
    ) {
        composable<Dest1.Profile> {
            val viewModel = koinInject<ProfileViewModel>()
            ProfileScreen(viewModel)
        }
        composable<Dest1.Settings> {
            val viewModel = koinInject<SettingsViewModel>()
            SettingsScreen(viewModel)
        }
    }
}