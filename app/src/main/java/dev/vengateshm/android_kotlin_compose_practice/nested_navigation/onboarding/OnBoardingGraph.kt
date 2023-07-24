package dev.vengateshm.android_kotlin_compose_practice.nested_navigation.onboarding

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import dev.vengateshm.android_kotlin_compose_practice.nested_navigation.models.AppScreen
import dev.vengateshm.android_kotlin_compose_practice.nested_navigation.sharedViewModel

fun NavGraphBuilder.OnBoardingGraph(navController: NavController) {
    navigation(
        startDestination = AppScreen.Onboarding.Login.name, route = AppScreen.Onboarding.name
    ) {
        composable(route = AppScreen.Onboarding.Login.name) {
            val viewModel = it.sharedViewModel<OnboardingViewModel>(navController = navController)
            LoginScreen(
                navController = navController,
                onLoginSuccess = {
                    println("Screen view count metrics : ${viewModel.screenVisitCount}")
                    navController.navigate(AppScreen.Home.name) {
                        popUpTo(AppScreen.Onboarding.name) {
                            inclusive = true
                        }
                    }
                },
                viewModel = viewModel
            )
        }
        composable(route = AppScreen.Onboarding.Register.name) {
            val viewModel = it.sharedViewModel<OnboardingViewModel>(navController = navController)
            RegisterScreen(viewModel = viewModel)
        }
        composable(route = AppScreen.Onboarding.ForgotPassword.name) {
            val viewModel = it.sharedViewModel<OnboardingViewModel>(navController = navController)
            ForgotPassword(viewModel = viewModel)
        }
    }
}