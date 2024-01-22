package dev.vengateshm.android_kotlin_compose_practice.modifiers

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MarqueeModifier() {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Lorem ipsum dolor sit, Lorem ipsum dolor sit, Lorem ipsum dolor sit, Lorem ipsum dolor sit, Lorem ipsum dolor sit, Lorem ipsum dolor sit, Lorem ipsum dolor sit",
            maxLines = 1,
            modifier = Modifier.basicMarquee(),
        )
    }
}

@Preview
@Composable
fun MarqueeModifierPreview() {
    MarqueeModifier()
}
