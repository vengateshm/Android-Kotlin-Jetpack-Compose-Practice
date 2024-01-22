package dev.vengateshm.android_kotlin_compose_practice.compose_apis.remember_updated_state

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import kotlinx.coroutines.delay

@Composable
fun Screen() {
    var number by remember { mutableStateOf(0) }
    Column {
        Header(number)
        Button(onClick = { number++ }) {
            Text(text = "Click!")
        }
    }
}

@Composable
fun Header(number: Int) {
    val updatedNumber by rememberUpdatedState(newValue = number)
    // println("Header (Re)composed $number")
    println("Header (Re)composed $updatedNumber")
    LaunchedEffect(key1 = Unit) {
        // Adding a delay of 5 seconds. If button clicked multiple times
        // before delay over, the number will be updated but
        // below println prints 0 instead latest value
        // because launched effect reads the initial stale value
        delay(5000L)
        // println("Effect called $number")
        println("Effect called $updatedNumber")
    }
    Text(text = "$number")
}

@Composable
fun Parent() {
    val counter by produceState(initialValue = 0) {
        repeat(10) {
            delay(50L)
            value++
        }
    }
    val lambda: () -> Unit =
        if (counter % 2 == 0) {
            { println("Even : $counter") }
        } else {
            { println("Odd : $counter") }
        }
    Child(lambda)
}

@Composable
fun Child(lambda: () -> Unit) {
    val updatedLambda by rememberUpdatedState(newValue = lambda)
    LaunchedEffect(key1 = Unit) {
        delay(1000L)
        updatedLambda()
    }
}
