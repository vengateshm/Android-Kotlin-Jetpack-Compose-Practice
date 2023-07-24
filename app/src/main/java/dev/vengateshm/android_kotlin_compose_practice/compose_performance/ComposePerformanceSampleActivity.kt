package dev.vengateshm.android_kotlin_compose_practice.compose_performance

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import kotlinx.coroutines.delay

class ComposePerformanceSampleActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                Parent()
            }
        }
    }
}

@Composable
fun Parent() {
    SideEffect {
        println("Parent composed")
    }
    var text by remember { mutableStateOf("") }

    LaunchedEffect(key1 = Unit) {
        delay(5000L)
        text = "Hello"
    }
    /**
     * Logs:
     * Parent composed
     * ChildWithoutLambda composed
     * Parent composed
     * ChildWithoutLambda composed
     * */
//    ChildWithoutLambda(text = text)
    /**
     * Logs:
     * Parent composed
     * ChildWithLambda composed
     * ChildWithLambda composed
     * */
    ChildWithLambda(text = { text })
}

@Composable
fun ChildWithoutLambda(text: String) {
    SideEffect {
        println("ChildWithoutLambda composed")
    }
    Text(text = text)
}

@Composable
fun ChildWithLambda(text: () -> String) {
    SideEffect {
        println("ChildWithLambda composed")
    }
    Text(text = text())
}