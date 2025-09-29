package dev.vengateshm.compose_material3.api_compose.side_effects

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import kotlin.time.Duration.Companion.seconds

@Composable
fun ProduceStateSample() {
  val post by produceState<Result<Post>?>(initialValue = null) {
    value = getPost()
  }

  Box(
    modifier = Modifier.fillMaxSize(),
    contentAlignment = Alignment.Center,
  ) {
    post
      ?.onSuccess {

      }
      ?.onFailure {

      }
      ?: CircularProgressIndicator()
  }
}

suspend fun getPost(): Result<Post> {
  return withContext(Dispatchers.IO) {
    delay(3.seconds)
    Result.success(Post(id = 1))
  }
}

data class Post(
  val id: Int,
  val title: String = "",
  val description: String = "",
)

class ProduceStateViewModel : ViewModel() {
  fun fromCallback(callback: (String) -> Unit) {
    callback("From Callback")
  }

  suspend fun fromSuspend(): String {
    delay(3.seconds)
    return "From Suspend"
  }

  fun fromFlow() = flow {
    delay(3.seconds)
    emit("From Flow : Value = C")
    delay(3.seconds)
    emit("From Flow : Value = A")
  }
}

@Composable
fun ProduceStateSample1(modifier: Modifier = Modifier) {
  val viewModel = viewModel<ProduceStateViewModel>()

  val state1 by produceState(initialValue = "Loading...", viewModel) {
    viewModel.fromCallback { value = it }
  }

  val state2 by produceState(initialValue = "Loading...", viewModel) {
    value = viewModel.fromSuspend()
  }

  val lifecycle = LocalLifecycleOwner.current.lifecycle
  val state3 by produceState(initialValue = "Loading...", viewModel) {
    viewModel.fromFlow().flowWithLifecycle(lifecycle)
      .collect { value = it }
  }

  Column(
    modifier = modifier
      .fillMaxSize()
      .safeDrawingPadding()
      .padding(16.dp),
  ) {
    Text(text = state1)
    Text(text = state2)
    Text(text = state3)
  }
}