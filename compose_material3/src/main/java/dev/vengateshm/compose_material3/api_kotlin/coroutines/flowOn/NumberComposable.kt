package dev.vengateshm.compose_material3.api_kotlin.coroutines.flowOn

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.CoroutineDispatcher

@Composable
fun NumberComposable(modifier: Modifier = Modifier) {
  val viewModel = viewModel<NumberViewModel>()
  LaunchedEffect(Unit) {
    println("UI collecting on: ${this.coroutineContext[CoroutineDispatcher]}")

    viewModel.numbersFlow.collect {
      println("UI received: $it")
    }
  }
}