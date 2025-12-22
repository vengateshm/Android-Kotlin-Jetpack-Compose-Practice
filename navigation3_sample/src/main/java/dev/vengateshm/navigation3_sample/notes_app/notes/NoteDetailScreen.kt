package dev.vengateshm.navigation3_sample.notes_app.notes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun NoteDetailsScreen(
  modifier: Modifier = Modifier,
  viewModel: NoteDetailViewModel,
) {
  val noteState by viewModel.noteState.collectAsStateWithLifecycle()
  Column(
    modifier = modifier
      .fillMaxSize()
      .background(noteState.color)
      .padding(16.dp),
  ) {
    Text(
      text = noteState.title,
      fontSize = 26.sp,
    )
    Spacer(modifier = Modifier.height(16.dp))
    Text(
      text = noteState.title,
      fontSize = 18.sp,
    )
  }
}