package dev.vengateshm.compose_material3.ui_concepts.predictive_back_animations

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun PredictiveBackSample(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    Scaffold {
        NavHost(
            modifier = Modifier.padding(it),
            navController = navController,
            startDestination = "screenA",
        ) {
            composable(route = "screenA") {
                SheetA(
                    onNext = { navController.navigate("screenB") },
                )
            }
            composable(route = "screenB") {
                SheetScreen()
            }
        }
    }
}