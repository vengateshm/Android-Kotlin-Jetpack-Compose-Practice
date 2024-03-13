package dev.vengateshm.compose_material3.animation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import kotlin.random.Random

@Composable
fun ChangeFloatColorAsState() {
    var scale by remember {
        mutableFloatStateOf(1f)
    }
    var color by remember {
        mutableStateOf(Color.Blue)
    }

    val scope = rememberCoroutineScope()

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(100.dp)
                .scale(scale)
                .background(color = color)
                .clickable {
                    // Animation will happen one after another
                    /*scale += 0.1f
                    color = Color(
                        red = Random.nextInt(255),
                        green = Random.nextInt(255),
                        blue = Random.nextInt(255)
                    )*/

                    // Animation will happen simultaneously
                    scope.launch {
                        scale += 0.1f
                    }
                    scope.launch {
                        color = Color(
                            red = Random.nextInt(255),
                            green = Random.nextInt(255),
                            blue = Random.nextInt(255)
                        )
                    }
                }
        )
    }
}