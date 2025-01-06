package dev.vengateshm.compose_material3.api_android.credential_manager

import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext

@Composable
fun GoogleSignInSample(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult(),
    ) {
        GoogleSignInUtils.doGoogleSignIn(
            context = context,
            scope = scope,
            launcher = null,
            login = {
                Toast.makeText(context, "Signed In", Toast.LENGTH_SHORT).show()
            },
        )
    }

    Column(
        modifier = modifier.safeContentPadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Button(
            onClick = {
                GoogleSignInUtils.doGoogleSignIn(
                    context = context,
                    scope = scope,
                    launcher = launcher,
                    login = {
                        Toast.makeText(context, "Signed In", Toast.LENGTH_SHORT).show()
                    },
                )
            },
        ) {
            Text(text = "Google Sign In")
        }
    }
}