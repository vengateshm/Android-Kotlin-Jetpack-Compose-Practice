package dev.vengateshm.android_kotlin_compose_practice.compose_apis.composition_local_provider

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

val LocalCounter = compositionLocalOf { mutableStateOf(0) }

@Composable
fun CounterButton() {
    val counterState = LocalCounter.current
    Button(onClick = { counterState.value++ }) {
        Text(text = "Counter: ${counterState.value}")
    }
}

@Composable
fun MyComposable() {
    val state = remember {
        mutableStateOf(0)
    }
    CompositionLocalProvider(LocalCounter provides state) {
        Column {
            CounterButton()
            CounterButton()
            CounterButton()
        }
    }
}