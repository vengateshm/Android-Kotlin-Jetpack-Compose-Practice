package dev.vengateshm.compose_material3.custom_ui.confetti

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.withFrameNanos
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import kotlin.math.cos
import kotlin.math.sin
import kotlin.random.Random

@Composable
fun ConfettiCelebration(
    modifier: Modifier = Modifier,
    direction: ConfettiDirection = ConfettiCelebrationDefaults.Direction,
    shape: ConfettiParticleShape = ConfettiCelebrationDefaults.Shape,
    shouldRotateSquareShape: Boolean = ConfettiCelebrationDefaults.ShouldRotateSquareShape,
    confettiCount: Int = ConfettiCelebrationDefaults.ParticleCount,
    particleSizeRange: IntRange = ConfettiCelebrationDefaults.ParticleSizeRange,
    particleSpeedRange: ClosedFloatingPointRange<Float> = ConfettiCelebrationDefaults.ParticleSpeedRange,
    fadeConfig: FadeConfig = ConfettiCelebrationDefaults.FadeConfig,
    repeatModeConfig: RepeatModeConfig = ConfettiCelebrationDefaults.RepeatMode,
    emissionModeConfig: EmissionModeConfig = ConfettiCelebrationDefaults.EmissionMode,
    startConfetti: Boolean = true,
    content: @Composable ColumnScope.() -> Unit,
) {
    val particles = remember { mutableStateListOf<ConfettiParticle>() }
    var animationTick by remember { mutableLongStateOf(0) }
    val isTopToBottom =
        direction == ConfettiDirection.TopToBottom || direction == ConfettiDirection.RandomTopToBottom
    val isRandom =
        direction == ConfettiDirection.RandomTopToBottom || direction == ConfettiDirection.RandomBottomToTop

    BoxWithConstraints(
        modifier = modifier.fillMaxSize(),
    ) {
        val widthPx = this.constraints.maxWidth.toFloat()
        val heightPx = this.constraints.maxHeight.toFloat()

        LaunchedEffect(startConfetti) {
            if(!startConfetti) return@LaunchedEffect

            particles.clear()
            repeat(confettiCount) {
                val size = Random.nextInt(particleSizeRange.first, particleSizeRange.last + 1).dp
                val speed =
                    Random.nextFloat() * (particleSpeedRange.endInclusive - particleSpeedRange.start) + particleSpeedRange.start
                particles += ConfettiParticle(
                    x = Random.nextFloat() * widthPx,
                    y = if (isTopToBottom) 0f else heightPx,
                    speedX = if (isRandom) Random.nextFloat() * 2f - 1f else 0f,
                    speedY = if (isTopToBottom) speed else -speed,
                    size = size,
                    color = Color(
                        red = Random.nextFloat(),
                        green = Random.nextFloat(),
                        blue = Random.nextFloat(),
                        alpha = 1f,
                    ),
                    shape = shape,
                    rotation = if (shape == ConfettiParticleShape.Square && shouldRotateSquareShape) Random.nextFloat() * 360f else 0f,
                )
            }
        }

        LaunchedEffect(particles) {
//            while (true) {
//                particles.forEach {
//                    it.x += it.speedX
//                    it.y += it.speedY
//
//                    if (it.y !in 0f..heightPx || it.x !in 0f..widthPx) {
//                        // Reset
//                        it.x = Random.nextFloat() * widthPx
//                        it.y = if (direction is ConfettiDirection.TopToBottom) 0f else heightPx
//                    }
//                }
//                animationTick++
//                delay(16) // 16ms FPS
//            }

            if (particles.isNotEmpty()) {
                val fadeThreshold = fadeConfig.startThresholdFraction * heightPx
                var lastFrameTime = 0L
                while (true) {
                    withFrameNanos { frameTime ->
                        if (lastFrameTime != 0L) {
                            val deltaSec = (frameTime - lastFrameTime) / 1_000_000_000f
                            val iterator = particles.iterator()
                            while (iterator.hasNext()) {
                                val p = iterator.next()
                                p.x += p.speedX * deltaSec * 60f
                                p.y += p.speedY * deltaSec * 60f
                                p.alpha = if (fadeConfig.enabled) {
                                    when {
                                        p.y < fadeThreshold -> 1f
                                        p.y in fadeThreshold..heightPx -> {
                                            val progress =
                                                (p.y - fadeThreshold) / (heightPx - fadeThreshold)
                                            1f - progress.coerceIn(0f, 1f)
                                        }

                                        else -> 0f
                                    }
                                } else 1f

                                if (emissionModeConfig == EmissionModeConfig.Continuous) {
                                    when (repeatModeConfig) {
                                        RepeatModeConfig.Restart -> {
                                            if (p.y !in 0f..heightPx || p.x !in 0f..widthPx) {
                                                p.x = Random.nextFloat() * widthPx
                                                p.y = if (isTopToBottom) 0f else heightPx
                                                p.alpha = 1f
                                            }
                                        }

                                        RepeatModeConfig.Reverse -> {
                                            if (p.y <= 0f || p.y >= heightPx) {
                                                p.speedY = -p.speedY
                                            }
                                            if (p.x <= 0f || p.x >= widthPx) {
                                                p.speedX = -p.speedX
                                            }
                                        }
                                    }
                                } else if (emissionModeConfig == EmissionModeConfig.Burst) {
                                    if (p.y !in 0f..heightPx) {
                                        iterator.remove()
                                    }
                                }
                            }
                            animationTick = frameTime
                            if (emissionModeConfig == EmissionModeConfig.Burst && particles.isEmpty()) {
                                return@withFrameNanos
                            }
                        }
                        lastFrameTime = frameTime
                    }
                    if (emissionModeConfig == EmissionModeConfig.Burst && particles.isEmpty()) {
                        break
                    }
                }
            }
        }

        Canvas(modifier = Modifier.fillMaxSize()) {
            animationTick
            particles.forEach { particle ->
                val sizeInPx = particle.size.toPx()
                when (particle.shape) {
                    ConfettiParticleShape.Square -> {
                        if (shouldRotateSquareShape) {
                            withTransform(
                                {
                                    rotate(
                                        degrees = particle.rotation,
                                        pivot = Offset(
                                            particle.x + sizeInPx / 2,
                                            particle.y + sizeInPx / 2,
                                        ),
                                    )
                                },
                            ) {
                                drawRect(
                                    color = particle.color.copy(alpha = particle.alpha),
                                    topLeft = Offset(particle.x, particle.y),
                                    size = Size(sizeInPx, sizeInPx),
                                )
                            }

                        } else {
                            drawRect(
                                color = particle.color.copy(alpha = particle.alpha),
                                topLeft = Offset(particle.x, particle.y),
                                size = Size(sizeInPx, sizeInPx),
                            )
                        }
                    }

                    ConfettiParticleShape.Circle -> {
                        drawCircle(
                            color = particle.color.copy(alpha = particle.alpha),
                            radius = sizeInPx / 2,
                            center = Offset(particle.x, particle.y),
                        )
                    }

                    ConfettiParticleShape.Triangle -> {
                        val path = Path().apply {
                            moveTo(particle.x + sizeInPx / 2, particle.y)
                            lineTo(particle.x, particle.y + sizeInPx)
                            lineTo(particle.x + sizeInPx, particle.y + sizeInPx)
                            close()
                        }
                        drawPath(path, color = particle.color.copy(alpha = particle.alpha))
                    }

                    ConfettiParticleShape.Star -> {
                        val path = Path()
                        val cx = particle.x + sizeInPx / 2
                        val cy = particle.y + sizeInPx / 2
                        val spikes = 5
                        val outerRadius = sizeInPx / 2
                        val innerRadius = sizeInPx / 4
                        val angle = Math.PI / spikes

                        for (i in 0 until spikes * 2) {
                            val r = if (i % 2 == 0) outerRadius else innerRadius
                            val a = i * angle
                            val x = (cx + cos(a) * r).toFloat()
                            val y = (cy + sin(a) * r).toFloat()
                            if (i == 0) path.moveTo(x, y) else path.lineTo(x, y)
                        }
                        path.close()
                        drawPath(path, color = particle.color.copy(alpha = particle.alpha))
                    }

                    ConfettiParticleShape.Heart -> {
                        val path = Path().apply {
                            val cx = particle.x + sizeInPx / 2
                            val cy = particle.y + sizeInPx / 2
                            moveTo(cx, cy + sizeInPx / 4)
                            cubicTo(
                                cx + sizeInPx / 2,
                                cy - sizeInPx / 4,
                                cx + sizeInPx,
                                cy + sizeInPx / 2,
                                cx,
                                cy + sizeInPx,
                            )
                            cubicTo(
                                cx - sizeInPx,
                                cy + sizeInPx / 2,
                                cx - sizeInPx / 2,
                                cy - sizeInPx / 4,
                                cx,
                                cy + sizeInPx / 4,
                            )
                        }
                        drawPath(path, color = particle.color.copy(alpha = particle.alpha))
                    }
                }
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            content = content,
        )
    }
}

object ConfettiCelebrationDefaults {
    val Shape: ConfettiParticleShape = ConfettiParticleShape.Square
    val Direction: ConfettiDirection = ConfettiDirection.TopToBottom
    val ParticleCount: Int = 100
    val ShouldRotateSquareShape: Boolean = false
    val ParticleSizeRange: IntRange = 4..12
    val ParticleSpeedRange: ClosedFloatingPointRange<Float> = 1f..4f
    val FadeConfig: FadeConfig = FadeConfig()
    val RepeatMode: RepeatModeConfig = RepeatModeConfig.Restart
    val EmissionMode: EmissionModeConfig = EmissionModeConfig.Continuous
}

data class FadeConfig(
    val enabled: Boolean = false,
    val startThresholdFraction: Float = 0.8f, // fraction of screen height where fading starts
)

enum class RepeatModeConfig {
    Restart,
    Reverse
}

enum class EmissionModeConfig {
    Continuous,   // Emits over time
    Burst,        // Emits once
}

@Composable
fun ConfettiDemo() {
    ConfettiCelebration(
        modifier = Modifier.fillMaxSize(),
        direction = ConfettiDirection.TopToBottom,
        shape = ConfettiParticleShape.Square,
    ) {
        Icon(
            imageVector = Icons.Default.Star,
            contentDescription = null,
            modifier = Modifier.size(64.dp),
            tint = Color(0xFFFF9100),
        )
        Text(
            text = "Congratulations!",
            style = MaterialTheme.typography.headlineMedium,
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ConfettiDemoPreview(
    @PreviewParameter(ConfettiCelebrationDirectionPreviewParameterProvider::class) direction: ConfettiDirection,
) {
    ConfettiCelebration(
        direction = direction,
        shape = ConfettiParticleShape.Star,
        particleSizeRange = 6..16,
        particleSpeedRange = 2f..5f,
        fadeConfig = FadeConfig(enabled = true, startThresholdFraction = 0.5f),
        repeatModeConfig = RepeatModeConfig.Restart,
        emissionModeConfig = EmissionModeConfig.Continuous,
    ) {
        Icon(
            imageVector = Icons.Default.Star,
            contentDescription = null,
            modifier = Modifier.size(64.dp),
            tint = Color(0xFFFF9100),
        )
        Text(
            text = "Congratulations!",
            style = MaterialTheme.typography.headlineMedium,
        )
    }
}

class ConfettiCelebrationDirectionPreviewParameterProvider :
    PreviewParameterProvider<ConfettiDirection> {
    override val values: Sequence<ConfettiDirection>
        get() = listOf(
            ConfettiDirection.TopToBottom,
            ConfettiDirection.BottomToTop,
            ConfettiDirection.RandomTopToBottom,
            ConfettiDirection.RandomBottomToTop,
        ).asSequence()
}