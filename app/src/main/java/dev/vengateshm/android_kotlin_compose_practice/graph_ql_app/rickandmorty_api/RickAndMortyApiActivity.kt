package dev.vengateshm.android_kotlin_compose_practice.graph_ql_app.rickandmorty_api

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.vengateshm.android_kotlin_compose_practice.graph_ql_app.rickandmorty_api.ui.screen.character.CharacterScreen
import dev.vengateshm.android_kotlin_compose_practice.graph_ql_app.rickandmorty_api.ui.screen.character_list.CharacterListScreen
import dev.vengateshm.android_kotlin_compose_practice.graph_ql_app.rickandmorty_api.ui.util.Route

class RickAndMortyApiActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = Route.characterList) {
                    composable(route = Route.characterList) {
                        CharacterListScreen(onCharacterClicked = { id ->
                            navController.navigate(route = Route.character.replace("{id}", id))
                        })
                    }
                    composable(route = Route.character) {
                        CharacterScreen(onNavigateBack = {
                            navController.popBackStack()
                        })
                    }
                }
            }
        }
    }
}
