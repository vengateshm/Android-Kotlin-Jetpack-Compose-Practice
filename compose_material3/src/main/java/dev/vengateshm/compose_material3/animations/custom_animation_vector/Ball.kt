package dev.vengateshm.compose_material3.animations.custom_animation_vector

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun Ball(modifier: Modifier = Modifier) {
  Box(
    modifier = modifier
      .background(
        color = Color.Blue,
        shape = CircleShape,
      ),
  )
}

@Preview
@Composable
fun BallPreview() {
  Ball(modifier = Modifier.size(size = 100.dp))
}
