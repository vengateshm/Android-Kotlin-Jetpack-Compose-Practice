package dev.vengateshm.compose_material3.custom_gestures.with_animations

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp

data class ColorItem(val color: Int, val name: String)

@Composable
fun ListScreen(
    modifier: Modifier = Modifier,
    onItemClick: (ColorItem) -> Unit
) {
    val colors = remember {
        listOf(
            ColorItem(Color.Red.toArgb(), "Red"),
            ColorItem(Color.Green.toArgb(), "Green"),
            ColorItem(Color.Blue.toArgb(), "Blue"),
            ColorItem(Color.Yellow.toArgb(), "Yellow"),
            ColorItem(Color.Magenta.toArgb(), "Magenta"),
            ColorItem(Color.Cyan.toArgb(), "Cyan"),
        )
    }
    LazyVerticalGrid(columns = GridCells.Fixed(2)) {
        items(colors) {
            Box(
                modifier = Modifier
                    .height(100.dp)
                    .background(color = Color(it.color))
                    .clickable {
                        onItemClick(it)
                    }
            )
        }
    }
}