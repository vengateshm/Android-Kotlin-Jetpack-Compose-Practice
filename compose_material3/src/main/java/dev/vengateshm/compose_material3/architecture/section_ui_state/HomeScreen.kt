package dev.vengateshm.compose_material3.architecture.section_ui_state

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.safeGesturesPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun SectionUi(modifier: Modifier = Modifier) {
  val viewModel = viewModel<HomeViewModel>()

  val state by viewModel.state.collectAsStateWithLifecycle()

  LazyColumn(
    modifier = Modifier
      .fillMaxSize()
      .safeContentPadding()
      .safeDrawingPadding()
      .safeGesturesPadding(),
  ) {
    item {
      TopRatedSection(state.topRated)
    }
    item {
      NotesSection(state.notes)
    }
    item {
      CardsSection(state.cards)
    }
    item {
      ItemsSection(state.items)
    }
  }
}

@Composable
fun ItemsSection(
  state: SectionUiState<List<String>>,
) {
  when (state) {
    SectionUiState.Loading -> CircularProgressIndicator()

    is SectionUiState.Success ->
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .background(Color.Gray)
          .padding(16.dp),
      ) {
        state.data.forEach {
          Text(it)
        }
      }

    is SectionUiState.Error -> Text(state.message)
  }
}

@Composable
fun CardsSection(
  state: SectionUiState<List<String>>,
) {
  when (state) {
    SectionUiState.Loading -> CircularProgressIndicator()

    is SectionUiState.Success ->
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .background(Color.LightGray)
          .padding(16.dp),
      ) {
        state.data.forEach {
          Text(it)
        }
      }

    is SectionUiState.Error -> Text(state.message)
  }
}

@Composable
fun NotesSection(
  state: SectionUiState<List<String>>,
) {
  when (state) {
    SectionUiState.Loading -> CircularProgressIndicator()

    is SectionUiState.Success ->
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .background(Color.Gray)
          .padding(16.dp),
      ) {
        state.data.forEach {
          Text(it)
        }
      }

    is SectionUiState.Error -> Text(state.message)
  }
}

@Composable
fun TopRatedSection(
  state: SectionUiState<List<String>>,
) {
  when (state) {
    SectionUiState.Loading -> CircularProgressIndicator()

    is SectionUiState.Success ->
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .background(Color.LightGray)
          .padding(16.dp),
      ) {
        state.data.forEach {
          Text(it)
        }
      }

    is SectionUiState.Error -> Text(state.message)
  }
}