package dev.vengateshm.testing.room_hilt.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "notes")
data class NoteEntity(
  @PrimaryKey(autoGenerate = true)
  val id: Long = 0,
  val title: String,
  val content: String,
  val createdAt: Date,
  val updatedAt: Date,
  val isFavorite: Boolean = false,
  val category: String = "General",
)