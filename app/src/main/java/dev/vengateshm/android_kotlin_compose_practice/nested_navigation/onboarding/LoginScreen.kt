package dev.vengateshm.android_kotlin_compose_practice.nested_navigation.onboarding

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import dev.vengateshm.android_kotlin_compose_practice.nested_navigation.models.AppScreen

@Composable
fun LoginScreen(
    navController: NavController,
    onLoginSuccess: () -> Unit,
    viewModel: OnboardingViewModel,
) {
    LaunchedEffect(key1 = Unit) {
        viewModel.updateScreenVisitCount(AppScreen.Onboarding.Login.name)
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Login\nNested Navigation"
        )
        Button(
            onClick = {
                onLoginSuccess()
            }) {
            Text(
                text = "Login"
            )
        }
        Button(
            onClick = {
                navController.navigate(AppScreen.Onboarding.Register.name)
            }) {
            Text(
                text = "Register"
            )
        }
        Button(
            onClick = { navController.navigate(AppScreen.Onboarding.ForgotPassword.name) }) {
            Text(
                text = "Forgot Password"
            )
        }
    }
}