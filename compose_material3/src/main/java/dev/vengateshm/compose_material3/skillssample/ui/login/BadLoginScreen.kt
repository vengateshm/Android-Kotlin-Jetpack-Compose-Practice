package dev.vengateshm.compose_material3.skillssample.ui.login

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable

@Composable
fun BadLoginScreen() {
  var email = ""
  var password = ""
  var isLoading = false
  var error = ""

  Column {
    TextField(
      value = email,
      onValueChange = { email = it },
    )

    TextField(
      value = password,
      onValueChange = { password = it },
    )

    if (isLoading) {
      CircularProgressIndicator()
    }

    Button(
      onClick = {
        isLoading = true

        if (email.isEmpty() || password.isEmpty()) {
          error = "Fields cannot be empty"
          isLoading = false
        } else {
          // fake login
          if (email == "test@test.com" && password == "123456") {
            isLoading = false
          } else {
            error = "Invalid credentials"
            isLoading = false
          }
        }
      },
    ) {
      Text("Login")
    }

    if (error.isNotEmpty()) {
      Text(error)
    }
  }
}