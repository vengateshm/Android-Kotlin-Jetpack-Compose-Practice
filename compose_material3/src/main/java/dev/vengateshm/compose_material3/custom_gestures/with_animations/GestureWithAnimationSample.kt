package dev.vengateshm.compose_material3.custom_gestures.with_animations

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun GestureWithAnimationSample(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "list_screen",
    ) {
        composable("list_screen") {
            ListScreen(onItemClick = {
                navController.navigate("detail_screen/${it.name}/${it.color}")
            })
        }
        composable("detail_screen/{name}/{color}") {
            val name = it.arguments?.getString("name") ?: ""
            val color = it.arguments?.getString("color")?.toIntOrNull() ?: 0
            DetailScreen(colorItem = ColorItem(color = color, name = name))
        }
    }
}