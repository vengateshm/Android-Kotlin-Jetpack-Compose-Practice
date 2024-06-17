package dev.vengateshm.compose_material3.testing.compose_with_view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp

const val WELCOME_SIGN_IN_BTN_TAG = "WELCOME_SIGN_IN_BTN_TAG"
const val WELCOME_SIGN_UP_BTN_TAG = "WELCOME_SIGN_UP_BTN_TAG"

@Composable
fun WelcomeScreen(
    modifier: Modifier = Modifier,
    onSignInClick: (String) -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.align(alignment = Alignment.BottomCenter)) {
            Button(
                modifier = Modifier.testTag(WELCOME_SIGN_IN_BTN_TAG),
                onClick = {
                    onSignInClick("signIn")
                }) {
                Text(text = "SIGN IN")
            }
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedButton(
                modifier = Modifier.testTag(WELCOME_SIGN_UP_BTN_TAG),
                onClick = {}) {
                Text(text = "SIGN UP")
            }
        }
    }
}