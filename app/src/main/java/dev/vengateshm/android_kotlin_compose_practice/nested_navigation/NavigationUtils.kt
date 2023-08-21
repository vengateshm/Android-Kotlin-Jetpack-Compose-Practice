package dev.vengateshm.android_kotlin_compose_practice.nested_navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController

/*@Composable
fun NavBackStackEntry.sharedViewModel(navController: NavController): OnboardingViewModel {
    val route = this.destination.parent?.route ?: return viewModel<OnboardingViewModel>()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(route)
    }
    return viewModel<OnboardingViewModel>(viewModelStoreOwner = parentEntry)
}*/

@Composable
inline fun <reified T : ViewModel> NavBackStackEntry.sharedViewModel(navController: NavController): T {
    val route = this.destination.parent?.route ?: return viewModel()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(route)
    }
    return viewModel(viewModelStoreOwner = parentEntry)
}