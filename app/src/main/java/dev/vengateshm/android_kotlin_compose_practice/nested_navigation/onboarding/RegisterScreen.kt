package dev.vengateshm.android_kotlin_compose_practice.nested_navigation.onboarding

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import dev.vengateshm.android_kotlin_compose_practice.nested_navigation.models.AppScreen

@Composable
fun RegisterScreen(viewModel: OnboardingViewModel) {
    LaunchedEffect(key1 = Unit) {
        viewModel.updateScreenVisitCount(AppScreen.Onboarding.Register.name)
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
    ) {
        Text(text = "Register\nNested Navigation")
    }
}
