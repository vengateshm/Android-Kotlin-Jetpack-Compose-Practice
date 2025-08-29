package dev.vengateshm.compose_material3.api_compose.brush

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asComposePath
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.graphics.shapes.CornerRounding
import androidx.graphics.shapes.Morph
import androidx.graphics.shapes.RoundedPolygon
import androidx.graphics.shapes.toPath

@Composable
fun MorphSample(modifier: Modifier = Modifier) {
  val infiniteAnimation = rememberInfiniteTransition(label = "infinite animation")
  val morphProgress by infiniteAnimation.animateFloat(
    initialValue = 0f,
    targetValue = 1f,
    animationSpec = infiniteRepeatable(
      animation = tween(500),
      repeatMode = RepeatMode.Reverse,
    ),
    label = "morph",
  )

  Box(
    modifier = Modifier
      .fillMaxSize()
      .drawWithCache {
        val triangle = RoundedPolygon(
          numVertices = 3,
          radius = size.minDimension / 2f,
          centerX = size.width / 2f,
          centerY = size.height / 2f,
          rounding = CornerRounding(
            size.minDimension / 10f,
            smoothing = 0.1f,
          ),
        )
        val square = RoundedPolygon(
          numVertices = 4,
          radius = size.minDimension / 2f,
          centerX = size.width / 2f,
          centerY = size.height / 2f,
        )

        val morph = Morph(start = triangle, end = square)
        val morphPath = morph
          .toPath(progress = morphProgress)
          .asComposePath()

        onDrawBehind {
          drawPath(morphPath, color = Color.Black)
        }
      },
  )
}

@Preview(showBackground = true, backgroundColor = 0XFFFFFFF)
@Composable
fun MorphSamplePreview(modifier: Modifier = Modifier) {
  Box(modifier = Modifier.size(200.dp)) {
    MorphSample()
  }
}