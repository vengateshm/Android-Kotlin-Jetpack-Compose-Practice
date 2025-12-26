package dev.vengateshm.compose_material3.api_compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun Parent(modifier: Modifier = Modifier) {
  var counter by remember { mutableIntStateOf(0) }
  SideEffect { println("Parent recompose") }
//  Child(count = counter, onIncrement = { counter++ })
  Child(count = { counter }, onIncrement = { counter++ })
}

@Composable
fun Child(count: Int, onIncrement: () -> Unit) {
  SideEffect { println("Child recompose") }
  Box(
    modifier = Modifier.fillMaxSize(),
    contentAlignment = Alignment.Center,
  ) {
    Button(onClick = onIncrement) {
      Text("Click me: $count")
    }
  }
}

@Composable
fun Child(count: () -> Int, onIncrement: () -> Unit) {
  SideEffect { println("Child recompose") }
  Box(
    modifier = Modifier.fillMaxSize(),
    contentAlignment = Alignment.Center,
  ) {
    Button(onClick = onIncrement) {
      Text("Click me: ${count()}")
    }
  }
}