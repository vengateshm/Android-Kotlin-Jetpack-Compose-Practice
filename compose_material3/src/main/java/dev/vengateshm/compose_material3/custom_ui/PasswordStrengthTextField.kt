package dev.vengateshm.compose_material3.custom_ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.vengateshm.compose_material3.utils.PasswordValidationResult
import dev.vengateshm.compose_material3.utils.passwordValidationResult

@Composable
fun PasswordStrengthTextField(modifier: Modifier = Modifier) {
    var password by remember { mutableStateOf("") }
    var passwordValidationResult by remember { mutableStateOf(listOf<PasswordValidationResult>()) }
    Column(modifier = Modifier.fillMaxSize()) {
        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
                passwordValidationResult = it.passwordValidationResult()
            })
        Spacer(modifier = Modifier.height(10.dp))
        AnimatedVisibility(visible = passwordValidationResult.isNotEmpty()) {
            Spacer(modifier = Modifier.height(6.dp))
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(text = "Your password")
                passwordValidationResult.forEach {
                    PasswordValidationInfo(msg = it.msg, isValid = it.isValid)
                }
            }
        }
    }
}

@Composable
fun PasswordValidationInfo(modifier: Modifier = Modifier, msg: String, isValid: Boolean) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(text = msg)
        if (isValid) Icon(
            imageVector = Icons.Default.CheckCircle,
            tint = Color.Green,
            contentDescription = "Correct"
        ) else Icon(
            imageVector = Icons.Default.Error,
            tint = Color.Red,
            contentDescription = "Error"
        )
    }
}

@Preview
@Composable
private fun PasswordStrengthTextFieldPreview() {
    PasswordStrengthTextField()
}