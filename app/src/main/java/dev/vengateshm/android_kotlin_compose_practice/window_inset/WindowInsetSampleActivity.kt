package dev.vengateshm.android_kotlin_compose_practice.window_inset

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.delay

class WindowInsetSampleActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                val colors = remember {
                    listOf(
                        Color(0xFFFFC400),
                        Color(0xFFFF9100),
                        Color(0xFFFF3D00),
                    )
                }
                var colorIndex by remember { mutableIntStateOf(0) }
                LaunchedEffect(Unit) {
                    colorIndex += 1
                }
                val animatedColor by animateColorAsState(
                    targetValue = colors[colorIndex],
                    animationSpec = tween(1500),
                    label = "status bar color animation"
                )
                val window = (LocalContext.current as Activity).window!!
                LaunchedEffect(colorIndex) {
                    window.statusBarColor = animatedColor.toArgb()
                    window.navigationBarColor = animatedColor.toArgb()
                    delay(1500)
                    if (colorIndex < colors.lastIndex) {
                        colorIndex += 1
                    } else {
                        colorIndex = 0
                    }
                }
            }
        }
    }
}