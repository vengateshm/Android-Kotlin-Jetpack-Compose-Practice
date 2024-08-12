package dev.vengateshm.compose_material3.ui_components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun LazyVerticalGridWithCustomSpanSample(modifier: Modifier = Modifier) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(count = 3),
    ) {
        items(20,
            span = { index ->
                if (index % 2 == 0) GridItemSpan(2) else GridItemSpan(1)
            }) {
            Text(
                text = it.toString(),
                modifier = Modifier
                    .padding(10.dp)
                    .background(color = Color.Red)
                    .padding(10.dp)
            )
        }
    }
}