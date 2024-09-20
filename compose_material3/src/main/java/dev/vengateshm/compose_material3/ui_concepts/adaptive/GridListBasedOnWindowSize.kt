package dev.vengateshm.compose_material3.ui_concepts.adaptive

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import dev.vengateshm.compose_material3.api_compose.preview_provider.ReferenceDevices
import dev.vengateshm.compose_material3.ui_concepts.multiple_screen_size_support.WindowType
import dev.vengateshm.compose_material3.ui_concepts.multiple_screen_size_support.getWindowSize
import kotlin.random.Random

@Composable
fun GridListBasedOnWindowSize(modifier: Modifier = Modifier) {
    val columnCells = when (getWindowSize().width) {
        WindowType.Compact -> GridCells.Fixed(3)
        WindowType.Medium -> GridCells.Fixed(4)
        WindowType.Expanded -> GridCells.Adaptive(minSize = 140.dp)
    }
    LazyVerticalGrid(columns = columnCells) {
        items(100) { index ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .background(color = Color(Random.nextLong(0XFFFFFFFF))),
                contentAlignment = Alignment.Center,
            ) {
                Text(text = "Item ${index + 1}")
            }
        }
    }
}

@ReferenceDevices
@Composable
private fun GridListBasedOnWindowSizePreview() {
    MaterialTheme {
        Surface {
            GridListBasedOnWindowSize()
        }
    }
}