package dev.vengateshm.compose_material3.api_android.encrypted_datastore

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.datastore.dataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

private val Context.userPreferenceDatastore by dataStore(
    fileName = "user_preferences.pb",
    serializer = UserPreferencesSerializer(),
)

private val SECRET_TOKEN = (1..1000).map {
    (('a'..'z') + ('A'..'Z') + ('0'..'9')).random()
}.joinToString(separator = "")
private val SECRET_TOKEN_1 = "Hello Kotlin!"

@Composable
fun EncryptDatastoreSample(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    var decryptedToken by remember { mutableStateOf("") }
    Column(
        modifier = modifier
            .fillMaxSize()
            .safeContentPadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Button(
            onClick = {
                scope.launch {
                    context.userPreferenceDatastore.updateData {
                        UserPreferences(token = SECRET_TOKEN_1)
                    }
                }
            },
        ) {
            Text(text = "Encrypt")
        }
        Button(
            onClick = {
                scope.launch {
                    decryptedToken = context.userPreferenceDatastore.data.first().token ?: ""
                }
            },
        ) {
            Text(text = "Decrypt")
        }
        Text(text = decryptedToken)
    }
}