package dev.vengateshm.android_kotlin_compose_practice.nested_navigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.vengateshm.android_kotlin_compose_practice.nested_navigation.home.HomeScreen
import dev.vengateshm.android_kotlin_compose_practice.nested_navigation.models.AppScreen
import dev.vengateshm.android_kotlin_compose_practice.nested_navigation.onboarding.OnBoardingGraph

class NestedNavigationActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = AppScreen.Onboarding.name
                ) {
                    composable(route = AppScreen.Home.name) {
                        HomeScreen(navController = navController)
                    }
                    OnBoardingGraph(navController = navController)
                }
            }
        }
    }
}



