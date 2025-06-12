package dev.vengateshm.navigation3_sample.notes

import androidx.compose.ui.graphics.Color

data class Note(
    val id: Int,
    val title: String,
    val content: String,
    val color: Color,
)

val sampleNotes = List(100) {
    Note(
        id = it,
        title = "Note ${it + 1}",
        content = "Content of Note ${it + 1}",
        color = Color(
            red = (0..255).random(),
            green = (0..255).random(),
            blue = (0..255).random(),
        ).copy(alpha = .5f),
    )
}