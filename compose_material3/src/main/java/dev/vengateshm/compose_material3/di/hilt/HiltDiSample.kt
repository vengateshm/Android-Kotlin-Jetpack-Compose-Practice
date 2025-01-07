package dev.vengateshm.compose_material3.di.hilt

import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun HiltDiSample(
    modifier: Modifier = Modifier,
    assemblyAViewModelFactory: AssemblyBViewModel.AssemblyBViewModelFactory,
) {
    val navController = rememberNavController()
    NavHost(
        modifier = modifier.safeContentPadding(),
        navController = navController,
        startDestination = "assembly_a",
    ) {
        composable("assembly_a") {
            AssemblyA(
                goToNext = {
                    navController.navigate("assembly_b")
                },
            )
        }
        composable("assembly_b") {
            AssemblyB(assemblyAViewModelFactory = assemblyAViewModelFactory)
        }
    }
}