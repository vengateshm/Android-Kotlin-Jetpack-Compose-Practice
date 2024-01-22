package dev.vengateshm.android_kotlin_compose_practice.recomposition_debugging

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import kotlin.random.Random

private val LOG_TAG = "RecompositionDebugging"

class RecompositionDebuggingActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                ScreenContent()
            }
        }
    }
}

@Composable
fun ScreenContent() {
    Log.i(LOG_TAG, "Inside ScreenContent composable")
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        var color1 by remember {
            mutableStateOf(Color.White)
        }
        val color1Animated by animateColorAsState(
            targetValue = color1,
            animationSpec = tween(durationMillis = 1000),
        )
        var color2 by remember {
            mutableStateOf(Color.White)
        }
        var color3 by remember {
            mutableStateOf(Color.White)
        }
        Box(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .weight(1f)
//                .background(color1Animated)
                    .background(color1),
        )
        Box(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .background(color2),
        )
        Box(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .background(color3),
        )
        Button(onClick = {
            when (Random.nextInt(3)) {
                0 -> color1 = Color.random()
                1 -> color2 = Color.random()
                2 -> color3 = Color.random()
            }
        }) {
            Text(text = "Change random color")
        }
    }
}

fun Color.Companion.random(): Color {
    return Color(Random.nextLong(0xFFFFFFFF))
}
