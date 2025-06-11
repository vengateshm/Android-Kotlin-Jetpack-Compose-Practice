package dev.vengateshm.confetti_animation

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp

data class ConfettiParticle(
    var x: Float,
    var y: Float,
    var speedX: Float,
    var speedY: Float,
    val size: Dp,
    val color: Color,
    val shape: ConfettiParticleShape,
    val rotation: Float = 0f,
    var alpha: Float = 1f,
)
