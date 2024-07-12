package dev.vengateshm.compose_material3.animations

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

data class BarData(
    val width: Int,
    val height: Int,
    val newHeight: Int = height,
    val color: Color = Color.White,
    val heightAnimatable: Animatable<Float, AnimationVector1D> = Animatable(initialValue = height.toFloat()),
)

@Composable
fun BarGraphAnimationSample(modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        BarGraphAnimationAnimatable()
    }
}

@Composable
fun BarGraphAnimation(modifier: Modifier = Modifier) {
    val barDataList = remember {
        mutableStateListOf(
            BarData(width = 20, height = 40, color = Color.Red),
            BarData(width = 20, height = 70, color = Color.Blue),
            BarData(width = 20, height = 20, color = Color.Cyan),
            BarData(width = 20, height = 80, color = Color.Black),
            BarData(width = 20, height = 65, color = Color.Green),
        )
    }

    LaunchedEffect(Unit) {
        delay(300L)
        while (true) {
            barDataList.forEachIndexed { index, barData ->
                barDataList[index] = barData.copy(newHeight = Random.nextInt(100))
            }
            delay(100L)
        }
    }

    Box(modifier = Modifier.size(150.dp)) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.Bottom
        ) {
            barDataList.forEach { barData ->
                Box(
                    modifier = Modifier
                        .width(width = barData.width.dp)
                        .height(height = barData.newHeight.dp)
                        .background(color = barData.color)
                )
            }
        }
    }
}

@Composable
fun BarGraphAnimationAnimatable(modifier: Modifier = Modifier) {
    val barDataList = remember {
        mutableStateListOf(
            BarData(width = 20, height = 40, color = Color.Red),
            BarData(width = 20, height = 70, color = Color.Blue),
            BarData(width = 20, height = 20, color = Color.Cyan),
            BarData(width = 20, height = 80, color = Color.Black),
            BarData(width = 20, height = 65, color = Color.Green),
        )
    }

    LaunchedEffect(Unit) {
        while (true) {
            barDataList.forEachIndexed { index, _ ->
                launch {
                    barDataList[index].heightAnimatable.animateTo(
                        targetValue = Random.nextInt(100).toFloat(),
                        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy)
                    )
                }
            }
            delay(500L)
        }
    }

    Box(modifier = Modifier.size(150.dp)) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.Bottom
        ) {
            barDataList.forEach { barData ->
                Box(
                    modifier = Modifier
                        .width(width = barData.width.dp)
                        .height(height = barData.heightAnimatable.value.dp)
                        .background(color = barData.color)
                )
            }
        }
    }
}