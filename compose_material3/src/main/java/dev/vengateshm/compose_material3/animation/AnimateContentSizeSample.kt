package dev.vengateshm.compose_material3.animation

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

@Composable
fun AnimateContentSizeSample() {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        var showFullText by remember {
            mutableStateOf(false)
        }
        Text(
            text = "This is a long text.This is a long text.This is a long text.This is a long text.This is a long text.This is a long text.This is a long text.This is a long text.This is a long text.This is a long text.This is a long text.This is a long text.This is a long text.This is a long text.This is a long text.This is a long text.This is a long text.This is a long text.This is a long text.This is a long text.This is a long text.",
            maxLines = if (showFullText) Int.MAX_VALUE else 2,
            modifier = Modifier
                .clickable {
                    showFullText = !showFullText
                }
                .animateContentSize(
                    animationSpec = tween(
                        durationMillis = 1000,
                        easing = LinearEasing
                    )
                ))
    }
}