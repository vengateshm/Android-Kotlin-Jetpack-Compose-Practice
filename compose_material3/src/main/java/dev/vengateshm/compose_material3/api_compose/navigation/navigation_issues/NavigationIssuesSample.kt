package dev.vengateshm.compose_material3.api_compose.navigation.navigation_issues

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.serialization.Serializable

@Serializable
sealed class NavigationIssuesSampleScreen {
    @Serializable
    data class Home(val date: Long = 0L) : NavigationIssuesSampleScreen()

    @Serializable
    data class Details(val id: String = "") : NavigationIssuesSampleScreen()
}

@Composable
fun NavigationIssuesSample(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = NavigationIssuesSampleScreen.Home(),
    ) {
        composable<NavigationIssuesSampleScreen.Home> {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Button(
                    onClick = {
                        navController.navigate(NavigationIssuesSampleScreen.Details("123"))
                    },
                ) {
                    Text(text = "Go to Details")
                }
            }
        }
        composable<NavigationIssuesSampleScreen.Details> {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Button(
                    onClick = {
                        navController.navigateUp()
//                        navController.popBackStack() // When using this and upon double tap it pops previous composable also
                    },
                ) {
                    Text(text = "Go Back")
                }
                Button(
                    onClick = {
                        navController.navigate(NavigationIssuesSampleScreen.Home(date = 99)) {
                            launchSingleTop = true
//                            popUpTo(NavigationIssuesSampleScreen.Home()) { // Cause issues
//                                inclusive = true
//                            }
                            popUpTo<NavigationIssuesSampleScreen.Home>(NavigationIssuesSampleScreen.Home::class) {
                                inclusive = true
                            }
                        }
                    },
                ) {
                    Text(text = "Go To Home")
                }
            }
        }
    }
}