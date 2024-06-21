package dev.vengateshm.compose_material3.api_android.shared_preferences

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.edit
import dev.vengateshm.compose_material3.theme.JostRegular
import dev.vengateshm.compose_material3.theme.LocalRegularFontFamily

@Composable
fun EncryptedSharedPreferencesSample(modifier: Modifier = Modifier) {

    CompositionLocalProvider(LocalRegularFontFamily provides JostRegular) {
        val context = LocalContext.current
        val encryptedPreferences =
            remember { EncryptedPreferenceManager.encryptedPreferences(context) }

        var text by remember { mutableStateOf("") }
        var savedText by remember { mutableStateOf("") }

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(0.6f),
                textStyle = TextStyle(
                    fontFamily = LocalRegularFontFamily.current,
                    fontSize = 24.sp
                ),
                value = text,
                onValueChange = {
                    text = it
                },
                placeholder = {
                    Text(
                        text = "Your text here...",
                        fontFamily = LocalRegularFontFamily.current,
                        fontSize = 24.sp
                    )
                }
            )
            Spacer(modifier = Modifier.height(height = 24.dp))
            Button(
                modifier = Modifier.fillMaxWidth(0.6f),
                onClick = {
                    encryptedPreferences.edit {
                        putString("TEXT", text)
                    }
                    text = ""
                }) {
                Text(
                    text = "Save",
                    fontFamily = LocalRegularFontFamily.current,
                    fontSize = 24.sp
                )
            }
            Spacer(modifier = Modifier.height(height = 24.dp))
            Button(
                modifier = Modifier.fillMaxWidth(0.6f),
                onClick = {
                    savedText = encryptedPreferences.getString("TEXT", "") ?: ""
                }) {
                Text(
                    text = "Get",
                    fontFamily = LocalRegularFontFamily.current,
                    fontSize = 24.sp
                )
            }
            AnimatedVisibility(visible = savedText.isNotEmpty()) {
                Spacer(modifier = Modifier.height(height = 24.dp))
                Text(
                    text = savedText,
                    fontFamily = LocalRegularFontFamily.current,
                    fontSize = 24.sp
                )
            }
        }
    }
}