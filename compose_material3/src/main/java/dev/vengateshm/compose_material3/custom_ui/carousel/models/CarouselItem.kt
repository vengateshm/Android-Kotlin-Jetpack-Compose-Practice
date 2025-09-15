package dev.vengateshm.compose_material3.custom_ui.carousel.models

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import dev.vengateshm.compose_material3.R

data class CarouselItem(
  val id: Int,
  val title: String,
  val subtitle: String,
  val description: String,
  @DrawableRes val animatedIcon: Int,
  val backgroundColor: Color = Color.White,
)

val carouselItems = listOf(
  CarouselItem(
    id = 1,
    title = "Music Vibes",
    subtitle = "Music Vibes",
    description = "Feel the rhythm with this animated music icon. The pulsating animation sync perfectly with the beat, creating an immersive audio-visual experience.",
    animatedIcon = R.drawable.animated_star,
    backgroundColor = Color(0xFF9C27B0),
  ),
  CarouselItem(
    id = 2,
    title = "Home Sweet Home",
    subtitle = "Home Sweet Home",
    description = "A cozy home icon that welcomes users with its warm animation. The gentle glow effect makes users feel comfortable and at ease in your application.",
    animatedIcon = R.drawable.animated_star,
    backgroundColor = Color(0xFF2196F3),
  ),
  CarouselItem(
    id = 3,
    title = "Play Time",
    subtitle = "Play Time",
    description = "Get ready for action with this dynamic play button animation. The smooth transition from pause to play creates anticipation for media content.",
    animatedIcon = R.drawable.animated_star,
    backgroundColor = Color(0xFFFF5722),
  )
)