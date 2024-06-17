package dev.vengateshm.compose_material3.testing.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

@Composable
fun NavHostTest(navHostController: NavHostController) {
    NavHost(navController = navHostController, startDestination = "s1") {
        composable(route = "s1") {
            S1(onNavigateToS2 = {
                val value1 = "Hello!"
                val value2 = "Good Morning!"
                navHostController.navigate(
                    route = "s2/${value1}/${value2}"
                )
            })
        }
        composable(
            route = "s2/{value1}/{value2}",
            arguments = listOf(
                navArgument("value1") { type = NavType.StringType },
                navArgument("value2") { type = NavType.StringType })
        ) { navBackStackEntry ->
            S2(
                value1 = navBackStackEntry.arguments?.getString("value1") ?: "",
                value2 = navBackStackEntry.arguments?.getString("value2") ?: "",
                onGoBack = {
                    navHostController.navigate(route = "s1") {
                        popUpTo(route = "s2/{value1}/{value2}") { inclusive = true }
                    }
                }
            )
        }
    }
}

@Composable
fun S1(
    modifier: Modifier = Modifier,
    onNavigateToS2: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Button(onClick = {
            onNavigateToS2()
        }) {
            Text(
                text = "Go to S2",
                fontSize = 20.sp
            )
        }
    }
}

@Composable
fun S2(modifier: Modifier = Modifier, value1: String, value2: String, onGoBack: () -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column {
            Text(
                text = "$value1 $value2",
                fontSize = 20.sp
            )
            Button(onClick = {
                onGoBack()
            }) {
                Text(
                    text = "Go back",
                    fontSize = 20.sp
                )
            }
        }
    }
}