package dev.vengateshm.android_kotlin_compose_practice.modifiers

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.material.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.delay

val color1 = Color(0xFFE91E63)

@Composable
fun ModifiersSample() {
    var changeColor by remember { mutableStateOf(false) }

    LaunchedEffect(true){
        snapshotFlow {  }
        delay(5000L)
        changeColor = true
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .thenIf(changeColor) {
            Modifier.background(color1)
        }) {
        Text(
            text = "Modifiers Sample",
            color = contentColorFor(color1)
        )
    }
}

inline fun Modifier.thenIf(predicate: Boolean, modify: () -> Modifier): Modifier {
    return this.then(if (predicate) modify() else Modifier)
}