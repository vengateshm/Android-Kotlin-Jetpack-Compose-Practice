package dev.vengateshm.compose_material3.api_compose.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Element(modifier: Modifier = Modifier) {
    Spacer(modifier = Modifier.height(4.dp))
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(16.dp)
            .background(color = Color.Gray),
    )
    Spacer(modifier = Modifier.height(4.dp))
}