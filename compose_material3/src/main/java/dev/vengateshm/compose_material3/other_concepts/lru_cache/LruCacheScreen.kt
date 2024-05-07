package dev.vengateshm.compose_material3.other_concepts.lru_cache

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun LruCacheScreen() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "list") {
        composable("list") {
            Box(modifier = Modifier.fillMaxSize()) {
                Button(onClick = {
                    navController.navigate("detail")
                }) {
                    Text(text = "Open detail screen")
                }
            }
        }
        composable("detail") {
            CharacterDetailScreen()
        }
    }
}