package dev.vengateshm.navigation3_sample.notes_app.notes

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun NoteListScreen(
  onNoteClick: (Int) -> Unit,
  modifier: Modifier = Modifier,
) {
  LazyColumn(
    modifier = modifier
      .fillMaxSize(),
  ) {
    items(sampleNotes) { note ->
      Column(
        modifier = Modifier
          .fillMaxSize()
          .background(note.color)
          .clickable {
            onNoteClick(note.id)
          }
          .padding(8.dp),
      ) {
        Text(
          text = note.title,
          fontSize = 18.sp,
        )
        Text(text = note.content)
      }
    }
  }
}