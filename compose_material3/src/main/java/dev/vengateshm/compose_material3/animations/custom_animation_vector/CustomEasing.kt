package dev.vengateshm.compose_material3.animations.custom_animation_vector

import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.Easing
import kotlin.math.sin

val CustomCubicBezier = CubicBezierEasing(0.68f, -0.55f, 0.27f, 1.55f)
val SquaredEasing = Easing { fraction -> fraction * fraction }

fun SineWaveEasing(
  waveCount: Int = 3,
  amplitude: Float = 0.1f,
) = Easing { fraction ->
  fraction + amplitude * sin(fraction * waveCount * 2 * Math.PI).toFloat()
}