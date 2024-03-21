package dev.vengateshm.compose_material3.text

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.delay
import kotlin.random.Random

@Composable
fun TypeWriterText(
    text: String = "In this tutorial we will see how to make typewriter text",
    content: @Composable (String) -> Unit
) {
    var displayedText by remember {
        mutableStateOf("")
    }

    LaunchedEffect(key1 = Unit) {
        for (i in 1..text.length) {
            displayedText = text.substring(0, i)
            delay(100)
        }
    }

    content(displayedText)
}

@Preview
@Composable
private fun TypeWriterTextPreview() {
    TypeWriterText(
        text = "In this tutorial we will see how to make typewriter text"
    ) {
        Text(
            text = it,
            style = MaterialTheme.typography.displayLarge
        )
    }
}