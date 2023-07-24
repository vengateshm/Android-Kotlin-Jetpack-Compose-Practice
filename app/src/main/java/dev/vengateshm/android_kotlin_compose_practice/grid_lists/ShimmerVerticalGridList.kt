package dev.vengateshm.android_kotlin_compose_practice.grid_lists

import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@ExperimentalFoundationApi
@Composable
fun ShimmerVerticalGridListScreen() {
    LazyVerticalGrid(columns = GridCells.Fixed(2), modifier = Modifier.padding(8.dp)) {
        repeat(10) {
            item { ShimmerVerticalGridList() }
        }
    }
}

@Composable
fun ShimmerVerticalGridList() {

    val animateColors = listOf(
        Color.Gray.copy(0.9f),
        Color.Gray.copy(0.2f),
        Color.Gray.copy(0.9f)
    )
    val transition = rememberInfiniteTransition()
    val animState = transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            tween(
                durationMillis = 1200,
                easing = FastOutLinearInEasing
            ),
            RepeatMode.Reverse
        )
    )

    val brush = Brush.linearGradient(
        colors = animateColors,
        start = Offset(10f, 10f),
        end = Offset(animState.value, animState.value)
    )

    ShimmerGridItem(brush)
}

@Composable
fun ShimmerGridItem(brush: Brush) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(
            modifier = Modifier
                .width(100.dp)
                .height(100.dp)
                .background(brush = brush)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .height(20.dp)
                .background(brush = brush), text = ""
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            modifier = Modifier
                .height(15.dp)
                .width(80.dp)
                .background(brush = brush), text = ""
        )
    }
}