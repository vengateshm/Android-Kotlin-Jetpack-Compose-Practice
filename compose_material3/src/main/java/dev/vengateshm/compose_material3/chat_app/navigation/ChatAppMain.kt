package dev.vengateshm.compose_material3.chat_app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.vengateshm.compose_material3.chat_app.screens.ChatScreen
import dev.vengateshm.compose_material3.chat_app.screens.HomeScreen
import dev.vengateshm.compose_material3.chat_app.screens.StartScreen

@Composable
fun ChatAppMain() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = START) {
        composable(route = START) {
            StartScreen(navHostController = navController)
        }
        composable(route = HOME) {
            HomeScreen(navHostController = navController)
        }
        composable(route = CHAT) {
            ChatScreen(navHostController = navController)
        }
    }
}

const val START = "start_screen"
const val HOME = "home_screen"
const val CHAT = "chat_screen"