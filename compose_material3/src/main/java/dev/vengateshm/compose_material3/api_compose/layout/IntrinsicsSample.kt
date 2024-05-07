package dev.vengateshm.compose_material3.api_compose.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun IntrinsicsSample(modifier: Modifier = Modifier) {
    Column(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.background(color = Color.Cyan.copy(alpha = 0.2f)).width(IntrinsicSize.Min)) {
            Text(
                text = "Jetpack",
                modifier = Modifier.background(color = Color.LightGray)
            )
            Text(
                text = "Compose",
                modifier = Modifier.background(color = Color.LightGray)
            )
            Text(
                text = "Layout",
                modifier = Modifier.background(color = Color.LightGray)
            )
            Text(
                text = "And Modifiers",
                modifier = Modifier.background(color = Color.LightGray)
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Column(modifier = Modifier.background(color = Color.Cyan.copy(alpha = 0.2f)).width(IntrinsicSize.Max)) {
            Text(
                text = "Jetpack",
                modifier = Modifier.background(color = Color.LightGray)
            )
            Text(
                text = "Compose",
                modifier = Modifier.background(color = Color.LightGray)
            )
            Text(
                text = "Layout",
                modifier = Modifier.background(color = Color.LightGray)
            )
            Text(
                text = "And Modifiers",
                modifier = Modifier.background(color = Color.LightGray)
            )
        }
    }
}