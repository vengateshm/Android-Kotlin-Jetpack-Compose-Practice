package dev.vengateshm.compose_material3.custom_ui.scratch_card

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import dev.vengateshm.compose_material3.R

@Preview
@Composable
fun OuterLayer(modifier: Modifier = Modifier) {
  var pathState by remember { mutableLongStateOf(0L) }
  var totalDistance by remember { mutableStateOf(0f) }
  var isRevealed by remember { mutableStateOf(false) }

  val scratchPath = remember { Path() }
  val overlayImage = ImageBitmap.imageResource(R.drawable.scratch_overlay)

  val alpha by animateFloatAsState(
    targetValue = if (isRevealed) 0f else 1f,
    animationSpec = tween(durationMillis = 500),
    label = "AlphaAnimation",
  )

  val density = LocalDensity.current
  // Threshold distance in pixels. Here 400dp is used as a reveal trigger.
  val threshold = remember(density) { with(density) { 400.dp.toPx() } }

  Canvas(
    modifier = modifier
      .fillMaxSize()
      .graphicsLayer(
        alpha = alpha,
        compositingStrategy = CompositingStrategy.Offscreen,
      )
      .pointerInput(isRevealed) {
        if (!isRevealed) {
          detectDragGestures(
            onDragStart = { offset ->
              scratchPath.moveTo(offset.x, offset.y)
            },
            onDrag = { change, dragAmount ->
              change.consume()
              scratchPath.lineTo(change.position.x, change.position.y)
              pathState++
              totalDistance += dragAmount.getDistance()
              if (totalDistance > threshold) {
                isRevealed = true
              }
            },
          )
        }
      },
  ) {
    drawImage(
      image = overlayImage,
      dstSize = IntSize(size.width.toInt(), size.height.toInt()),
    )

    pathState

    drawPath(
      path = scratchPath,
      color = Color.Transparent,
      style = Stroke(
        width = 90f,
        cap = StrokeCap.Round,
        join = StrokeJoin.Round,
      ),
      blendMode = BlendMode.Clear,
    )
  }
}