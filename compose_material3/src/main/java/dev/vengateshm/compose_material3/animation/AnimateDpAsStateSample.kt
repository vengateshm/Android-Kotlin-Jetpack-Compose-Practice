package dev.vengateshm.compose_material3.animation

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AnimateDpAsStateSample() {
    var animate by remember {
        mutableStateOf(false)
    }

    val offsetX by animateDpAsState(
        targetValue = if (animate) 300.dp else 0.dp,
        /*animationSpec = tween(durationMillis = 2000),*/
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioHighBouncy,
        ),
        label = ""
    )
    Box(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .size(50.dp)
                .offset(x = offsetX),
            contentAlignment = Alignment.Center
        ) {
            Icon(imageVector = Icons.Filled.AddShoppingCart, contentDescription = "")
        }
        Button(
            modifier = Modifier.align(Alignment.Center),
            onClick = { animate = !animate }) {
            Text(text = "Animate")
        }
    }
}