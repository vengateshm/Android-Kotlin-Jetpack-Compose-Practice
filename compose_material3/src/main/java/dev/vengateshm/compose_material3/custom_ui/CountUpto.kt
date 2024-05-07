package dev.vengateshm.compose_material3.custom_ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

@Composable
fun CountUptoScreen() {
    var text by remember { mutableStateOf("") }

    CountUpto(max = 10) {
        text = it.toString()
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = text)
    }
}

@Composable
fun CountUpto(
    max: Int,
    onCountChange: (Int) -> Unit
) {

    var counter by remember {
        mutableIntStateOf(0)
    }

    LaunchedEffect(key1 = counter) {
        if (counter > max) {
            return@LaunchedEffect
        }
        delay(1.seconds)
        onCountChange(counter)
        counter++
    }
}

@Preview
@Composable
private fun CountUptoPreview() {
    CountUptoScreen()
}