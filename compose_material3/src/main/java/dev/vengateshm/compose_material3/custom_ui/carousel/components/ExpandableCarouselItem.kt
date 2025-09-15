package dev.vengateshm.compose_material3.custom_ui.carousel.components

import androidx.annotation.DrawableRes
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.graphics.res.animatedVectorResource
import androidx.compose.animation.graphics.res.rememberAnimatedVectorPainter
import androidx.compose.animation.graphics.vector.AnimatedImageVector
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.PlayCircleFilled
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.vengateshm.compose_material3.R
import dev.vengateshm.compose_material3.custom_ui.carousel.models.CarouselItem
import kotlinx.coroutines.delay

@Composable
fun ExpandableCarouselItem(
  item: CarouselItem,
  isExpanded: Boolean,
  onExpandToggle: () -> Unit,
  modifier: Modifier = Modifier,
) {
  val screenWidth = LocalWindowInfo.current.containerSize.width.dp

  /*val width by animateDpAsState(
    targetValue = if (isExpanded) screenWidth else 250.dp,
    label = "widthAnim",
  )
  val height by animateDpAsState(
    targetValue = if (isExpanded) 475.dp else 240.dp,
    label = "heightAnim",
  )*/
  val width = if (isExpanded) screenWidth else 250.dp
  val height = if (isExpanded) 475.dp else 240.dp

  var playAnimation by remember(isExpanded) { mutableStateOf(isExpanded) }

  Column(
    modifier = modifier
      .width(width)
      .height(height)
      .clip(RoundedCornerShape(16.dp))
      .background(color = Color.White)
      .clickable { onExpandToggle() }
      /*.animateContentSize(
        animationSpec = tween(durationMillis = 2000),
      )*/,
  ) {
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp),
    ) {
      BasicText(
        text = item.title,
        style = TextStyle(
          fontSize = 12.sp,
          color = Color(0XFF555555),
        ),
      )
      Spacer(modifier = Modifier.height(4.dp))
      BasicText(
        text = item.subtitle,
        style = TextStyle(
          fontSize = 16.sp,
          fontWeight = FontWeight.Bold,
          color = Color.Black,
        ),
      )
      if (isExpanded) {
        Spacer(modifier = Modifier.height(8.dp))
        BasicText(
          text = item.description,
          style = TextStyle(
            fontSize = 12.sp,
            color = Color.Black,
            lineHeight = 20.sp,
          ),
        )
      }
    }
    Box(
      modifier = Modifier
        .fillMaxSize()
        .clip(RoundedCornerShape(16.dp))
        .background(color = Color.White)
        .clickable { onExpandToggle() }
        .animateContentSize(
          animationSpec = tween(durationMillis = 300),
        ),
    ) {
      IconButton(
        modifier = Modifier.align(Alignment.TopEnd),
        onClick = { onExpandToggle() },
      ) {
        Icon(
          imageVector = if (isExpanded) Icons.Default.Close else Icons.Default.PlayCircleFilled,
          contentDescription = null,
          tint = Color.Blue,
        )
      }
      AnimatedCardImage(
        modifier = Modifier
          .size(100.dp)
          .align(Alignment.Center),
        drawableId = R.drawable.animated_star,
        playAnimation = playAnimation,
        onAnimationFinish = {
          playAnimation = false
        },
      )
    }
  }
}

const val ANIMATION_DURATION = 10000L

@Composable
fun AnimatedCardImage(
  modifier: Modifier = Modifier,
  @DrawableRes drawableId: Int,
  playAnimation: Boolean = false,
  onAnimationFinish: () -> Unit = {},
) {
  val image = AnimatedImageVector.animatedVectorResource(drawableId)

  LaunchedEffect(playAnimation) {
    if (playAnimation) {
      delay(ANIMATION_DURATION)
      onAnimationFinish()
    }
  }

  val painter = rememberAnimatedVectorPainter(
    animatedImageVector = image,
    atEnd = playAnimation,
  )

  Image(
    modifier = modifier,
    painter = painter,
    contentDescription = null,
  )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ExpandableCarouselItemPreview() {
  var isExpanded by remember { mutableStateOf(false) }
  val item = CarouselItem(
    id = 1,
    title = "Title",
    subtitle = "Subtitle",
    description = "This is a sample description for the card. It can be a bit longer to see how it wraps and fits within the expanded card.",
    animatedIcon = R.drawable.animated_star,
    backgroundColor = Color.Blue.copy(alpha = 0.7f),
  )
  Box(
    modifier = Modifier.fillMaxSize(),
    contentAlignment = Alignment.Center,
  ) {
    ExpandableCarouselItem(
      item = item,
      isExpanded = isExpanded,
      onExpandToggle = {
        isExpanded = !isExpanded
      },
    )
  }
}