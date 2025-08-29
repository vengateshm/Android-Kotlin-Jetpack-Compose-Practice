package dev.vengateshm.compose_material3.animations.custom_animation_vector

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween

enum class AnimationType {
  SPRING, TWEEN, KEYFRAME;

  fun <T> getAnimationSpec(): AnimationSpec<T> {
    return when (this) {
      SPRING -> spring<T>(
        dampingRatio = Spring.DampingRatioMediumBouncy,
        stiffness = Spring.StiffnessMedium,
      ) as AnimationSpec<T>

      TWEEN -> tween<T>(
        durationMillis = 1000,
        easing = SineWaveEasing(),
      ) as AnimationSpec<T>

      KEYFRAME -> keyframes<T> {
        durationMillis = 1000
        // 0f atFraction .2f LinearEasing
      } as AnimationSpec<T>
    }
  }
}