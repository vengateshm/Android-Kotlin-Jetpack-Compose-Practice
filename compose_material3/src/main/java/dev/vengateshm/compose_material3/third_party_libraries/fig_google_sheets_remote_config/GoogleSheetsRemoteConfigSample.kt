package dev.vengateshm.compose_material3.third_party_libraries.fig_google_sheets_remote_config

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import com.github.theapache64.fig.Fig
import kotlinx.coroutines.launch

@Composable
fun GoogleSheetsRemoteConfigSample(modifier: Modifier = Modifier) {
    val fig = remember { Fig() }
    val scope = rememberCoroutineScope()

    var wish by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = wish)
        Button(onClick = {
            scope.launch {
                fig.init(sheetUrl = "https://docs.google.com/spreadsheets/d/1kPuYwzfbnbF6vCW1s3Rw3geFiUqQGlq4OM5V78aoT0w/edit?usp=sharing")
                wish = fig.getValue("wish", "") ?: ""
            }
        }) {
            Text(text = "Get Value")
        }
    }
}