package dev.vengateshm.compose_material3.api_compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.movableContentOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun MovableContentOfSample(
    checked: Boolean,
    content: @Composable () -> Unit
) {
    val movableContent = remember {
        movableContentOf(content)
    } // Prevents dropping and recreating if already present
    if (checked) {
        Row {
            Text(text = "Checked")
            movableContent()
        }
    } else {
        Column {
            Text(text = "Unchecked")
            movableContent()
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MovableContentOfSamplePreview() {
    MovableContentOfSample(checked = false) {
        Text(text = "Content")
    }
}