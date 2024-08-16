package dev.vengateshm.compose_material3.api_compose.custom_modifiers

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp

@Composable
fun BrushWithTileModeSample(modifier: Modifier = Modifier) {
    val colors = remember { listOf(Color.Yellow, Color.Red, Color.Blue) }
    val tileSize = with(LocalDensity.current) { 25.dp.toPx() }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Box(
            modifier = Modifier
                .requiredSize(100.dp)
                .background(
                    brush = Brush.horizontalGradient(
                        colors = colors,
                        endX = tileSize,
                        tileMode = TileMode.Mirror
                    )
                )
        )
        Box(
            modifier = Modifier
                .requiredSize(100.dp)
                .background(
                    brush = Brush.horizontalGradient(
                        colors = colors,
                        endX = tileSize,
                        tileMode = TileMode.Repeated
                    )
                )
        )
        Box(
            modifier = Modifier
                .requiredSize(100.dp)
                .background(
                    brush = Brush.horizontalGradient(
                        colors = colors,
                        endX = tileSize,
                        tileMode = TileMode.Clamp
                    )
                )
        )
        Box(
            modifier = Modifier
                .requiredSize(100.dp)
                .background(
                    brush = Brush.horizontalGradient(
                        colors = colors,
                        endX = tileSize,
                        tileMode = TileMode.Decal
                    )
                )
        )
    }
}