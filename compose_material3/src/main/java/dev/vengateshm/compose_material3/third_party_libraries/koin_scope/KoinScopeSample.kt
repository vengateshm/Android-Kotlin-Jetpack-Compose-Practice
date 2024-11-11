package dev.vengateshm.compose_material3.third_party_libraries.koin_scope

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun KoinScopeSample(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = KoinScopeScreens.Entry) {
        composable<KoinScopeScreens.Entry>{
            EntryScreen {
                navController.navigate(KoinScopeScreens.Auth)
            }
        }
        composable<KoinScopeScreens.Auth>{
            val viewModel = viewModel<AuthScreenViewModel>()
            AuthScreen(
                onNext = {
                    navController.navigate(KoinScopeScreens.Landing)
                },
                viewModel = viewModel
            )
        }
        composable<KoinScopeScreens.Landing>{
            val viewModel = viewModel<LandingScreenViewModel>()
            LandingScreen(viewModel = viewModel)
        }
    }
}