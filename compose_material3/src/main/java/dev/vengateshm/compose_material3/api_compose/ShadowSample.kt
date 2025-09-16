package dev.vengateshm.compose_material3.api_compose

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.MaterialShapes
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.toShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.draw.innerShadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun ShadowSample(modifier: Modifier = Modifier) {
  Column(
    modifier = modifier
      .fillMaxSize()
      .padding(16.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    Box(
      modifier = Modifier
        .size(150.dp, 100.dp)
        .dropShadow(
          shape = RoundedCornerShape(20.dp),
          shadow = Shadow(
            radius = 10.dp,
            spread = 6.dp,
            color = Color(0X40000000),
            offset = DpOffset(x = 4.dp, y = 4.dp),
          ),
        ),
    ) {
      Column(
        modifier = Modifier
          .fillMaxSize()
          .background(
            color = Color.White,
            shape = RoundedCornerShape(20.dp),
          ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
      ) {
        Text(text = "Hello, World!")
      }
    }
    Spacer(modifier = Modifier.height(32.dp))
    Box(
      modifier = Modifier
        .size(150.dp, 150.dp)
        .dropShadow(
          shape = MaterialShapes.Cookie9Sided.toShape(),
          shadow = Shadow(
            radius = 10.dp,
            spread = 6.dp,
            color = Color(0X40000000),
            offset = DpOffset(x = 4.dp, y = 4.dp),
          ),
        ),
    ) {
      Column(
        modifier = Modifier
          .fillMaxSize()
          .background(
            color = Color.White,
            shape = MaterialShapes.Cookie9Sided.toShape(),
          ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
      ) {
        Text(text = "Hello, World!")
      }
    }
    Spacer(modifier = Modifier.height(32.dp))
    Box(
      modifier = Modifier
        .size(150.dp, 100.dp)
        .background(
          color = Color.White,
          shape = RoundedCornerShape(20.dp),
        )
        .innerShadow(
          shape = RoundedCornerShape(20.dp),
          shadow = Shadow(
            radius = 10.dp,
            spread = 2.dp,
            color = Color(0X40000000),
            offset = DpOffset(x = 6.dp, y = 6.dp),
          ),
        ),
    ) {
      Column(
        modifier = Modifier
          .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
      ) {
        Text(text = "Inner Shadow")
      }
    }
    Spacer(modifier = Modifier.height(32.dp))
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val blueDropShadowColor = Color(0x5C007AFF)
    val darkBlueDropShadowColor = Color(0x66007AFF)
    val greyInnerShadowColor1 = Color(0x1A007AFF)
    val greyInnerShadowColor2 = Color(0x1A007AFF)
    val transition = updateTransition(
      targetState = isPressed,
      label = "button_press_transition",
    )
    val blueDropShadow by transition.animateColor(
      label = "shadow_color",
      transitionSpec = { buttonPressAnimation() },
    ) { pressed ->
      if (pressed) Color.Transparent else blueDropShadowColor
    }
    val darkBlueDropShadow by transition.animateColor(
      label = "shadow_color",
      transitionSpec = { buttonPressAnimation() },
    ) { pressed ->
      if (pressed) Color.Transparent else darkBlueDropShadowColor
    }
    val innerShadowColor1 by transition.animateColor(
      label = "shadow_color",
      transitionSpec = { buttonPressAnimation() },
    ) { pressed ->
      if (pressed) greyInnerShadowColor1
      else greyInnerShadowColor2
    }
    val innerShadowColor2 by transition.animateColor(
      label = "shadow_color",
      transitionSpec = { buttonPressAnimation() },
    ) { pressed ->
      if (pressed) Color(0x4D007AFF)
      else Color(0x1A007AFF)
    }
    Box(
      modifier = Modifier
        .size(300.dp, 200.dp)
        .clickable(
          interactionSource = interactionSource,
          indication = null,
          onClick = { },
        )
        .dropShadow(
          shape = RoundedCornerShape(70.dp),
          shadow = Shadow(
            radius = 10.dp,
            spread = 0.dp,
            color = blueDropShadow,
            offset = DpOffset(x = 0.dp, y = -(2).dp),
            alpha = 0.3f,
          ),
        )
        .dropShadow(
          shape = RoundedCornerShape(70.dp),
          shadow = Shadow(
            radius = 10.dp,
            spread = 0.dp,
            color = darkBlueDropShadow,
            offset = DpOffset(x = 2.dp, y = 6.dp),
            alpha = 0.3f,
          ),
        )
        .background(
          color = Color.White,
          shape = RoundedCornerShape(70.dp),
        )
        .innerShadow(
          shape = RoundedCornerShape(70.dp),
          shadow = Shadow(
            radius = 8.dp,
            spread = 4.dp,
            color = innerShadowColor2,
            offset = DpOffset(x = 4.dp, y = 0.dp),
          ),
        )
        .innerShadow(
          shape = RoundedCornerShape(70.dp),
          shadow = Shadow(
            radius = 20.dp,
            spread = 4.dp,
            color = innerShadowColor1,
            offset = DpOffset(x = 4.dp, y = 0.dp),
            alpha = 0.3f,
          ),
        ),
      contentAlignment = Alignment.Center,
    ) {
      Text(
        text = "Animated colored shadows",
        style = MaterialTheme.typography.bodyLarge,
        fontSize = 24.sp,
        color = Color.Black,
      )
    }
  }
}

fun <T> buttonPressAnimation() = tween<T>(
  durationMillis = 400,
  easing = EaseInOut,
)

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun ShadowSamplePreview() {
  ShadowSample()
}