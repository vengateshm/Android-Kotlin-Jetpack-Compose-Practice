package dev.vengateshm.compose_material3.custom_ui.avatar_list

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun AvatarList(
  modifier: Modifier = Modifier,
  avatars: List<Color>,
) {
  val size = 50.dp
  var offset = 0.dp
  var isSpread by remember { mutableStateOf(false) }
  val factor by animateFloatAsState(
    targetValue = if (isSpread) 1f else 2f,
    animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy),
  )
  Box(
    modifier = modifier
      .clickable {
        isSpread = !isSpread
      }
      .graphicsLayer {
        compositingStrategy = CompositingStrategy.Offscreen
      },
  ) {

    for (avatar in avatars) {
      Avatar(modifier = Modifier.offset(x = offset)) {
        Box(
          modifier = Modifier
            .size(size = size)
            .background(color = avatar),
        )
      }
      offset += size / factor
    }
  }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun AvatarListPreview() {
  MaterialTheme {
    Box(
      modifier = Modifier
        .fillMaxWidth()
        .height(100.dp)
        .background(
          brush = Brush.horizontalGradient(listOf(Color.Magenta, Color.Cyan)),
        ),
      contentAlignment = Alignment.Center,
    ) {
      AvatarList(
        modifier = Modifier.width(250.dp),
        avatars = listOf(Color.Red, Color.Green, Color.Blue, Color.Yellow, Color.Magenta),
      )
    }
  }
}