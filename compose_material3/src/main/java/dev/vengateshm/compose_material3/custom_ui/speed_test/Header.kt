package dev.vengateshm.compose_material3.custom_ui.speed_test

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun Header(modifier: Modifier = Modifier) {
    Text(
        modifier = modifier.padding(top = 48.dp, bottom = 16.dp),
        text = "SPEED TEST",
        color = Color.White,
        style = MaterialTheme.typography.headlineMedium
    )
}

@Preview
@Composable
private fun HeaderPreview() {
    Header()
}