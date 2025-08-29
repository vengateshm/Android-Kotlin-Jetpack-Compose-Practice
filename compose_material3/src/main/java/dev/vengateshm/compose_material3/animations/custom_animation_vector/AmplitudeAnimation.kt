package dev.vengateshm.compose_material3.animations.custom_animation_vector

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.delay

@Composable
fun AmplitudeAnimation(
  modifier: Modifier = Modifier,
  animationType: AnimationType = AnimationType.TWEEN,
) {
  val coroutineScope = rememberCoroutineScope()
//  val musicReader = remember { LoopingMusicReader(coroutineScope = coroutineScope) }
  val musicReader = remember { RandomMusicReader(coroutineScope = coroutineScope) }
  val amplitude by musicReader.amplitude.collectAsStateWithLifecycle()

  val screenHeight = LocalWindowInfo.current.containerSize.height

  val translationYAnimated by animateFloatAsState(
    targetValue = -amplitude * screenHeight,
//    animationSpec = animationType.getAnimationSpec(),
  )

  LaunchedEffect(Unit) {
    delay(1000)
    musicReader.play()
  }

  Box(
    modifier = Modifier
      .fillMaxSize()
      .background(Color.White),
    contentAlignment = Alignment.Center,
  ) {
    Ball(
      modifier = Modifier
        .size(size = 100.dp)
        .graphicsLayer {
//          translationY = -amplitude * screenHeight
          translationY = translationYAnimated
        },
    )
  }
}

@Preview
@Composable
fun AmplitudeAnimationPreview() {
  AmplitudeAnimation()
}

