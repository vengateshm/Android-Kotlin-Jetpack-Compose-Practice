package dev.vengateshm.compose_material3.image

import androidx.annotation.DrawableRes
import androidx.compose.animation.graphics.res.animatedVectorResource
import androidx.compose.animation.graphics.res.rememberAnimatedVectorPainter
import androidx.compose.animation.graphics.vector.AnimatedImageVector
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dev.vengateshm.compose_material3.R

@Composable
fun AnimatedVectorSample(
  modifier: Modifier = Modifier,
  @DrawableRes drawableId: Int
) {
  val image = AnimatedImageVector.animatedVectorResource(drawableId)
  var atEnd by remember { mutableStateOf(false) }

//    LaunchedEffect(Unit) {
//        delay(500)
//        atEnd = true
//    }

  val painter = rememberAnimatedVectorPainter(
    animatedImageVector = image,
    atEnd = atEnd
  )

  Image(
    painter = painter,
    contentDescription = null,
    modifier = modifier.clickable {
      atEnd = !atEnd
    }
  )
}

@Preview
@Composable
private fun AnimatedVectorSamplePreview() {
  AnimatedVectorSample(
    drawableId = R.drawable.animated_star,
  )
}