package dev.vengateshm.compose_material3.custom_ui.avatar_list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke

@Composable
fun Avatar(
  modifier: Modifier = Modifier,
  content: @Composable () -> Unit,
) {
  Box(
    modifier = modifier
      .drawWithContent {
        drawContent()
        drawCircle(
          color = Color.White,
          style = Stroke(5f),
          blendMode = BlendMode.Clear
        )
      }
      .clip(CircleShape),
  ) {
    content()
  }
}