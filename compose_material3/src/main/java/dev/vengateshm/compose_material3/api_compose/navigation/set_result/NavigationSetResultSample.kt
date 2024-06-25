package dev.vengateshm.compose_material3.api_compose.navigation.set_result

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import dev.vengateshm.compose_material3.api_compose.navigation.setResult

@Composable
fun NavigationSetResultSample(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "a1") {
        addA1(
            onGoToA2 = {
                navController.navigate(route = "a2")
            }
        )
        addA2(
            onResult = {
                navController.setResult(it)
                navController.popBackStack()
            }
        )
    }
}