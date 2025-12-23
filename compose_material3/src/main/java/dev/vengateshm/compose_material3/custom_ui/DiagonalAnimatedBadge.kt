package dev.vengateshm.compose_material3.custom_ui

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Composable
fun DiagonalAnimatedBadge(
  def: DiagonalAnimatedBadgeDef,
  modifier: Modifier = Modifier,
) {
  var isShimmering by remember { mutableStateOf(def.shouldStartShimmer) }

  LaunchedEffect(key1 = def.shouldStartShimmer) {
    if (isShimmering) {
      delay(def.animationDurationMillis)
    }
  }

  Box(
    modifier = modifier
      .height(def.height)
      .background(
        color = def.backgroundColor,
        shape = RoundedCornerShape(def.roundedCorner),
      )
      .clip(shape = RoundedCornerShape(def.roundedCorner))
      .diagonalShimmer(
        triggerAnimation = isShimmering,
        animationDurationMillis = def.animationDurationMillis,
      ),
    contentAlignment = Alignment.Center,
  ) {
    Box(
      modifier = Modifier
        .height(def.height)
        .background(color = def.backgroundColor),
    ) {
      Text(
        text = def.text,
        modifier = Modifier
          .height(def.height)
          .wrapContentHeight(align = Alignment.CenterVertically)
          .padding(
            vertical = 8.dp,
            horizontal = 16.dp,
          ),
        textAlign = TextAlign.Center,
        style = TextStyle(
          color = def.textColor,
          fontWeight = FontWeight.Bold,
          textAlign = TextAlign.Center,
          fontSize = def.textSize,
        ),
      )
    }
  }
}

@Composable
fun Modifier.diagonalShimmer(
  triggerAnimation: Boolean = true,
  shimmerLineColor: Color = Color.White.copy(alpha = 0.7f),
  animationDurationMillis: Long,
  modifier: Modifier = Modifier,
): Modifier = composed {

  if (!triggerAnimation) return@composed this

  val transition: Animatable<Float, AnimationVector1D> = remember(true) {
    Animatable(initialValue = 0f)
  }

  LaunchedEffect(key1 = triggerAnimation) {
    if (triggerAnimation) {

      transition.animateTo(
        targetValue = 600f,
        animationSpec = tween(
          durationMillis = animationDurationMillis.toInt(),
          easing = LinearEasing,
        ),
      )

      transition.snapTo(0f)
      delay(3000)

      transition.animateTo(
        targetValue = 600f,
        animationSpec = infiniteRepeatable(
          animation = tween(
            durationMillis = animationDurationMillis.toInt(),
            easing = LinearEasing,
          ),
          repeatMode = RepeatMode.Restart,
        ),
      )
    }
  }

  val translateValue = transition.value

  val transparentColor = Color.Transparent
  val shimmerColors = listOf(
    transparentColor,
    transparentColor,
    shimmerLineColor,
    shimmerLineColor,
    shimmerLineColor,
    transparentColor,
    transparentColor,
  )

  return@composed this.then(
    Modifier.drawWithContent {
      val diagonalLength = 55f
      val startOffset =
        Offset(x = translateValue - diagonalLength, y = translateValue - diagonalLength)
      val endOffset = Offset(x = translateValue, y = translateValue)
      val brush = Brush.linearGradient(
        colors = shimmerColors,
        start = startOffset,
        end = endOffset,
      )

      drawContent()
      drawRect(
        brush = brush,
        size = size,
      )
    },
  )
}

@Preview
@Composable
private fun DiagonalAnimatedBadgePreview() {
  DiagonalAnimatedBadge(
    def = DiagonalAnimatedBadgeDef(
      text = "SEE WHAT'S NEW",
    ),
  )
}

data class DiagonalAnimatedBadgeDef(
  val text: String,
  val textColor: Color = Color.White,
  val textSize: TextUnit = 16.sp,
  val height: Dp = 66.dp,
  val backgroundColor: Color = Color.Blue,
  val roundedCorner: Dp = 16.dp,
  val shouldStartShimmer: Boolean = true,
  val animationDurationMillis: Long = 2500L,
)