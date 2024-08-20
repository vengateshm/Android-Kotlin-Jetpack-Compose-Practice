package dev.vengateshm.compose_material3.api_compose.slot_apis

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun SlotButton(content: @Composable RowScope.() -> Unit) {
    Row {
        content()
    }
}

@Composable
fun SlotUI(modifier: Modifier = Modifier) {
    SlotButton {
        Icon(imageVector = Icons.Default.Build, contentDescription = "build icon")
        Text(text = "Build")
    }
}

@Preview(showBackground = true)
@Composable
private fun SlotUIPreview() {
    SlotUI()
}