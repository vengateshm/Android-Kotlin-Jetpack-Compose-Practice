package dev.vengateshm.compose_material3.api_kotlin.mutex

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

@Composable
fun MutexCounterSample(modifier: Modifier = Modifier) {
    var counter by remember { mutableIntStateOf(0) }
    val mutex = remember { Mutex() }

//    LaunchedEffect(Unit) {
//        repeat(10) {
//            launch {
//                delay(1000)
//                counter += 1
//            }
//        }
//    }

    // With mutex
    LaunchedEffect(Unit) {
        repeat(10) {
            launch {
                mutex.lock()
                try {
                    delay(1000)
                    counter += 1
                } finally {
                    mutex.unlock()
                }
            }
        }
    }

    // With mutex
    LaunchedEffect(Unit) {
        repeat(10) {
            launch {
                mutex.withLock {
                    try {
                        delay(1000)
                        if (it == 5)
                            throw IllegalArgumentException("")
                        counter += 1
                    } catch (e: Exception) {
                        println(e)
                    }
                }
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = counter.toString())
        Text(text = "Counter")
    }
}