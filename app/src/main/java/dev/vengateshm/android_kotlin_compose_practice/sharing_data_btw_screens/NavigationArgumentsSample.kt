package dev.vengateshm.android_kotlin_compose_practice.sharing_data_btw_screens

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

/**
 * Use when need to pass stateless data from one screen to another
 * not in multiple screen scenarios
 * */
@Composable
fun NavigationArgumentsSample() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "screen1"
    ) {
        composable("screen1") {
            Screen1(
                onNavigateToScreen2 = {
                    navController.navigate("screen2/$it")
                }
            )
        }
        composable(route = "screen2/{my_param}",
            arguments = listOf(
                navArgument(name = "my_param") {
                    type = NavType.StringType
                }
            )
        ) {
            // Survives process death as nav arguments are put in saved state handle
            val myParam = it.arguments?.getString("my_param") ?: ""
            Screen2(myParam)
        }
    }
}

@Composable
fun Screen1(onNavigateToScreen2: (String) -> Unit) {
    Button(onClick = { onNavigateToScreen2("Hello World!") }) {
        Text(text = "Click!")
    }
}

@Composable
fun Screen2(myParam: String) {
    Text(text = myParam)
}