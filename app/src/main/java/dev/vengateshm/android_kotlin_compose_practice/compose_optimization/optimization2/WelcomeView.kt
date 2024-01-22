package dev.vengateshm.android_kotlin_compose_practice.compose_optimization.optimization2

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import dev.vengateshm.common_lib.ExternalUser

@Composable
fun WelcomeView(user: ExternalUser) {
    Text(text = "Welcome ${user.username}")
}
