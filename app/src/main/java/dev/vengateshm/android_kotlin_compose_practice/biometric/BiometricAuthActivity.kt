package dev.vengateshm.android_kotlin_compose_practice.biometric

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.fragment.app.FragmentActivity

class BiometricAuthActivity : FragmentActivity() {

    // FragmentActivity extends ComponentActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val biometricAuthenticator = BiometricAuthenticator(this)

        setContent {
            MaterialTheme {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    val fragmentActivity = LocalContext.current as FragmentActivity
                    var message by remember {
                        mutableStateOf("")
                    }
                    TextButton(onClick = {
                        biometricAuthenticator.promptBiometricAuth(
                            title = "Login",
                            subTitle = "Use your fingerprint or face is",
                            negativeButtonText = "Cancel",
                            fragmentActivity = fragmentActivity,
                            onSuccess = {
                                message = "Success"
                            },
                            onError = { _, errorString ->
                                message = errorString
                            },
                            onFailed = {
                                message = "Wrong fingerprint or face id"
                            }
                        )
                    }) {
                        Text(text = "Login with fingerprint or face id")
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = message)
                }
            }
        }
    }
}