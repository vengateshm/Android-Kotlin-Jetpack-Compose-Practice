package dev.vengateshm.android_kotlin_compose_practice.performance_tips

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import kotlin.math.pow

@Composable
fun PerfTip5() {
    val count =
        remember {
            mutableStateOf(0)
        }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Button(onClick = {
            count.value++
        }) {
            Text(text = "Increase")
        }
        val currentHeavyResult = 50.0.pow(50.0)
        // If we read here then it will look for nearest composable
        // and recomposes it
        Composable1 {
            "${count.value}"
        }
        Composable2(text = "$currentHeavyResult")
        println("Composition Composable Column")
    }
}

@Composable
fun Composable1(onText: () -> String) {
    println("Composition Composable1")
    Text(onText(), fontSize = 40.sp)
}

@Composable
fun Composable2(text: String) {
    println("Composition Composable2")
    Text(text, fontSize = 25.sp)
}
