package dev.vengateshm.navigation3_sample.notes_app.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun RegisterScreen(
  viewModel: RegisterViewModel,
  sharedAuthViewModel: SharedAuthViewModel,
  modifier: Modifier = Modifier,
) {
  val localCounter by viewModel.counter.collectAsStateWithLifecycle()
  val sharedCounter by sharedAuthViewModel.counter.collectAsStateWithLifecycle()

  Column(
    modifier.fillMaxSize(),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center,
  ) {
    Button(onClick = viewModel::bump) {
      Text("Local counter : $localCounter")
    }
    Button(onClick = sharedAuthViewModel::bump) {
      Text("Shared counter : $sharedCounter")
    }
  }
}