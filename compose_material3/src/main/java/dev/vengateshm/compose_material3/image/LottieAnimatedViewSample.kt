package dev.vengateshm.compose_material3.image

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import dev.vengateshm.compose_material3.R

@Composable
fun LottieAnimatedViewSample(modifier: Modifier = Modifier) {
  val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.success))
  val progress by animateLottieCompositionAsState(
    composition = composition,
    iterations = LottieConstants.IterateForever,
    isPlaying = true,
  )

  LottieAnimation(
    composition = composition,
    progress = { progress },
    modifier = modifier,
  )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun LottieAnimatedViewSamplePreview() {
  LottieAnimatedViewSample()
}