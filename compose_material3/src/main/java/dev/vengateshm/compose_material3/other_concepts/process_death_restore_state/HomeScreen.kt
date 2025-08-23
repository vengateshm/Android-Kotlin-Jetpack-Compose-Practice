package dev.vengateshm.compose_material3.other_concepts.process_death_restore_state

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlin.random.Random

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
  val viewModel = viewModel<HomeViewModel>()

  var rememberSaveableNumber by rememberSaveable { mutableIntStateOf(0) }
  val viewModelRetainedNumber = viewModel.retainedFavoriteNumber
  val viewModelNumber = viewModel.favoriteNumber

  Column(
    modifier = Modifier.fillMaxSize(),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    Text(text = "rememberSaveable Number: $rememberSaveableNumber")
    Spacer(modifier = Modifier.height(height = 12.dp))
    Text(text = "ViewModel Retained Number: $viewModelRetainedNumber")
    Spacer(modifier = Modifier.height(height = 12.dp))
    Text(text = "ViewModel Number: $viewModelNumber")
    Spacer(modifier = Modifier.height(height = 24.dp))
    Button(
      onClick = {
        rememberSaveableNumber = Random.nextInt(from = 1, until = 100)
        viewModel.updateNumber(Random.nextInt(from = 1, until = 100))
        viewModel.updateRetainedNumber(Random.nextInt(from = 1, until = 100))
      },
    ) {
      Text(text = "Generate New Number")
    }
  }
}