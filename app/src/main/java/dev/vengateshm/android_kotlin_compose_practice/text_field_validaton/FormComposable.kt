package dev.vengateshm.android_kotlin_compose_practice.text_field_validaton

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun FormComposable(viewModel: FormViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {

    val passwordValidationResult by viewModel.passwordValidationState.collectAsStateWithLifecycle()

    Column(modifier = Modifier.fillMaxSize()) {
        TextField(
            value = viewModel.password,
            onValueChange = { viewModel.onPasswordChanges(it) },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
        )
        if (passwordValidationResult.hasError) {
            Spacer(modifier = Modifier.height(16.dp))
            passwordValidationResult.errors.forEach { error ->
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = error.message,
                    color = Color.Red
                )
            }
        }
    }
}