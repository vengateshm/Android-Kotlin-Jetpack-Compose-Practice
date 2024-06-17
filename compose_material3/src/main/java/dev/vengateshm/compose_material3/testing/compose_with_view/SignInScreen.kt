package dev.vengateshm.compose_material3.testing.compose_with_view

import android.content.Intent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp

const val SIGN_IN_EMAIL_INPUT_TAG = "SIGN_IN_EMAIL_INPUT_TAG"
const val SIGN_IN_PASSWORD_INPUT_TAG = "SIGN_IN_PASSWORD_INPUT_TAG"
const val SIGN_IN_CONTINUE_BTN_TAG = "SIGN_IN_CONTINUE_BTN_TAG"

@Composable
fun SignInScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.align(alignment = Alignment.Center)
        ) {
            OutlinedTextField(
                modifier = Modifier.testTag(SIGN_IN_EMAIL_INPUT_TAG),
                value = email,
                onValueChange = {
                    email = it
                },
                label = { Text(text = "Email") }
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                modifier = Modifier.testTag(SIGN_IN_PASSWORD_INPUT_TAG),
                value = password,
                onValueChange = {
                    password = it
                },
                label = { Text(text = "Password") }
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                modifier = Modifier.testTag(SIGN_IN_CONTINUE_BTN_TAG),
                onClick = {
                    val intent = Intent(context, FeedActivity::class.java)
                    context.startActivity(intent)
                }) {
                Text(text = "CONTINUE")
            }
        }
    }
}