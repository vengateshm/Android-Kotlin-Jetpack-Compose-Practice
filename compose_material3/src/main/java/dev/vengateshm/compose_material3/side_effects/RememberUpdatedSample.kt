package dev.vengateshm.compose_material3.side_effects

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

@Composable
fun RememberUpdatedSample() {

    var btnColor by remember {
        mutableStateOf("Unknown")
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { btnColor = "RED" }) {
            Text(text = "Red")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = { btnColor = "BLACK" }) {
            Text(text = "Black")
        }
    }

//    Timeout(btnColor = btnColor)
    Timeout1(btnColor = btnColor)
}

@Composable
fun Timeout(btnColor: String) {
    LaunchedEffect(key1 = null) {
        delay(10.seconds)
        Log.d("tag", "Timeout ended")
        Log.d("tag", "Last pressed button color $btnColor")
    }
}

@Composable
fun Timeout1(btnColor: String) {

    val btnColorUpdated by rememberUpdatedState(newValue = btnColor)

    LaunchedEffect(key1 = null) {
        delay(10.seconds)
        Log.d("tag", "Timeout ended")
        Log.d("tag", "Last pressed button color $btnColorUpdated")
    }
}