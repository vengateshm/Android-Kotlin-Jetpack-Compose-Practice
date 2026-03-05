package dev.vengateshm.compose_material3.architecture.mvvm_mvi_combined

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun CombinedFlowsScreen(
  viewModel: CombineFlowsViewModel = viewModel(),
) {

  val state by viewModel.state.collectAsState()

  Box(
    modifier = Modifier.fillMaxSize(),
  ) {

    Column(
      modifier = Modifier.fillMaxSize(),
    ) {

      Button(
        onClick = { viewModel.loadData() },
        modifier = Modifier.padding(16.dp),
      ) {
        Text("Load Data")
      }

      LazyColumn(
        modifier = Modifier.fillMaxSize(),
      ) {

        items(state.items) { item ->

          Text(
            text = item,
            modifier = Modifier
              .fillMaxWidth()
              .padding(16.dp),
          )

          Divider()
        }
      }
    }

    if (state.isLoading) {

      CircularProgressIndicator(
        modifier = Modifier.align(Alignment.Center),
      )
    }

    state.errorMessage?.let { error ->

      Text(
        text = error,
        color = MaterialTheme.colorScheme.error,
        modifier = Modifier
          .align(Alignment.BottomCenter)
          .padding(16.dp),
      )
    }
  }
}