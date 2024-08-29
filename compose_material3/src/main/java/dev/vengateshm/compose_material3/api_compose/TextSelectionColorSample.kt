package dev.vengateshm.compose_material3.api_compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun TextSelectionColorSample(modifier: Modifier = Modifier) {
    val textSelectionColors = TextSelectionColors(
        handleColor = Color.Blue.copy(alpha = 0.4f),
        backgroundColor = Color.Green.copy(alpha = 0.4f),
    )
    CompositionLocalProvider(LocalTextSelectionColors provides textSelectionColors) {
        SelectionContainer {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                Text(text = "This text can be selected")
            }
        }
    }
}