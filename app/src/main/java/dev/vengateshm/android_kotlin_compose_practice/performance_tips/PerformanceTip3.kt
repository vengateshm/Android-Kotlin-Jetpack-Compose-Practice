package dev.vengateshm.android_kotlin_compose_practice.performance_tips

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlin.math.roundToInt

@Composable
fun PerfTip3() {
    var count = remember {
        mutableStateOf(0)
    }
    LaunchedEffect(key1 = true) {
        for (i: Int in 0..10000) {
            delay(5)
            count.value++
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ProgressComposable(progress = count)
    }
}

@Composable
fun ProgressComposable(progress: State<Int>) {
    val progressState = remember {
        derivedStateOf {
            // Emits if value different from last one
            (progress.value / 100f).roundToInt()
        }
    }
    println("Progress Composition: ${progressState.value}")
    Text(text = "Progress: ${progressState.value} %", fontSize = 25.sp)
}
