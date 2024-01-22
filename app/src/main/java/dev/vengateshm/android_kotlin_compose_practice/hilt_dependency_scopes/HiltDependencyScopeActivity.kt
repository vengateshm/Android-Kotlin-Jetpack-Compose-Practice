package dev.vengateshm.android_kotlin_compose_practice.hilt_dependency_scopes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HiltDependencyScopeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "start_screen",
                ) {
                    navigation(
                        startDestination = "feed_list",
                        route = "feed_graph",
                    ) {
                        composable("feed_list") { backStackEntry ->
                            val parent =
                                remember(backStackEntry) {
                                    navController.getBackStackEntry("feed_graph")
                                }
                            val viewModel = hiltViewModel<SessionViewModel>(parent)
                        }
                        composable("feed_detail") { backStackEntry ->
                            val parent =
                                remember(backStackEntry) {
                                    navController.getBackStackEntry("feed_graph")
                                }
                            val viewModel = hiltViewModel<SessionViewModel>(parent)
                        }
                    }
                    composable("start_screen") {
                    }
                }
            }
        }
    }
}
