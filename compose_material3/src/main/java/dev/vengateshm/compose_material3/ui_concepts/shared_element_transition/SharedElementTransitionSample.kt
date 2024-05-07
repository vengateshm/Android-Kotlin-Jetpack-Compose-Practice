package dev.vengateshm.compose_material3.ui_concepts.shared_element_transition

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController

@Composable
fun SharedElementTransitionSample(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    SharedElementTransitionNavGraph(navController)
}