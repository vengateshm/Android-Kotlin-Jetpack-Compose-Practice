package dev.vengateshm.compose_material3.animations

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

class ShakingState(
    private val strength: Strength,
    private val direction: Direction
) {
    val xPosition = Animatable(0f)

    suspend fun shake(durationMillis: Int = 1000) {
        val shakeAnimation: AnimationSpec<Float> = tween(durationMillis = durationMillis)
        when (direction) {
            Direction.LEFT -> shakeToLeft(shakeAnimation)
            Direction.RIGHT -> shakeToRight(shakeAnimation)
            Direction.LEFT_THEN_RIGHT -> shakeToLeftAndThenRight(shakeAnimation)
            Direction.RIGHT_THEN_LEFT -> shakeToRightAndThenLeft(shakeAnimation)
        }
    }

    private suspend fun shakeToLeft(shakeAnimation: AnimationSpec<Float>) {
        repeat(3) {
            // Move left
            xPosition.animateTo(targetValue = -strength.value, animationSpec = shakeAnimation)
            // Come back to original position
            xPosition.animateTo(targetValue = 0f, animationSpec = shakeAnimation)
        }
    }

    private suspend fun shakeToRight(shakeAnimation: AnimationSpec<Float>) {
        repeat(3) {
            // Move right
            xPosition.animateTo(targetValue = strength.value, animationSpec = shakeAnimation)
            // Come back to original position
            xPosition.animateTo(targetValue = 0f, animationSpec = shakeAnimation)
        }
    }

    private suspend fun shakeToLeftAndThenRight(shakeAnimation: AnimationSpec<Float>) {
        repeat(3) {
            xPosition.animateTo(targetValue = -strength.value, animationSpec = shakeAnimation)
            xPosition.animateTo(targetValue = 0f, animationSpec = shakeAnimation)
            xPosition.animateTo(targetValue = strength.value, animationSpec = shakeAnimation)
            xPosition.animateTo(targetValue = 0f, animationSpec = shakeAnimation)
        }
    }

    private suspend fun shakeToRightAndThenLeft(shakeAnimation: AnimationSpec<Float>) {
        repeat(3) {
            xPosition.animateTo(targetValue = strength.value, animationSpec = shakeAnimation)
            xPosition.animateTo(targetValue = 0f, animationSpec = shakeAnimation)
            xPosition.animateTo(targetValue = -strength.value, animationSpec = shakeAnimation)
            xPosition.animateTo(targetValue = 0f, animationSpec = shakeAnimation)
        }
    }

    sealed class Strength(val value: Float) {
        data object Normal : Strength(17f)
        data object Strong : Strength(40f)
        data class Custom(val strength: Float) : Strength(strength)
    }

    enum class Direction {
        LEFT,
        RIGHT,
        LEFT_THEN_RIGHT,
        RIGHT_THEN_LEFT
    }
}

@Composable
fun rememberShakingState(strength: ShakingState.Strength, direction: ShakingState.Direction) =
    remember {
        ShakingState(strength = strength, direction = direction)
    }

@Composable
fun ShakingAnimationSample(modifier: Modifier = Modifier) {
    val shakingState = rememberShakingState(
        strength = ShakingState.Strength.Strong,
        direction = ShakingState.Direction.LEFT_THEN_RIGHT
    )
    val coroutineScope = rememberCoroutineScope()
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextButton(
            modifier = Modifier.shakeable(shakingState),
            onClick = {
                coroutineScope.launch {
                    shakingState.shake(durationMillis = 100)
                }
            }) {
            Text(text = "Click to shake!", fontSize = 24.sp)
        }
    }
}

fun Modifier.shakeable(
    state: ShakingState
) =
    this.graphicsLayer {
        translationX = state.xPosition.value
    }