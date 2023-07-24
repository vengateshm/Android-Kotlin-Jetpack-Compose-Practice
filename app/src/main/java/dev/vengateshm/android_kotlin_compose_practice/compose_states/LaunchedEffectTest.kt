package dev.vengateshm.android_kotlin_compose_practice.compose_states

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext

@Composable
fun LaunchedEffectTest() {
    var state by remember { mutableStateOf(SomeData("Hello")) }
    val context = LocalContext.current

    LaunchedEffect(key1 = state) {
        // Called after first composition
        // Called when key changes
        // boolean - true to false and vice versa
        // object - based on equals method
        Toast.makeText(context, "Hello! 💕", Toast.LENGTH_SHORT).show()
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Text(
            modifier = Modifier.clickable {
                state = SomeData("World")
            },
            text = "Hello World! 🎶 $state"
        )
    }
}

data class SomeData(val someText: String)