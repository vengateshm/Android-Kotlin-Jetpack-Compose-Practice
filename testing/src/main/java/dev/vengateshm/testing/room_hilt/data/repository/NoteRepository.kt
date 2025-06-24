package dev.vengateshm.testing.room_hilt.data.repository

import dev.vengateshm.testing.room_hilt.data.dao.NoteDao
import dev.vengateshm.testing.room_hilt.data.entities.NoteEntity
import kotlinx.coroutines.flow.Flow
import java.util.Date

class NoteRepository(private val noteDao: NoteDao) {

  //fun getAllNotes(): Flow<List<Note>> = noteDao.getAllNotes()

  suspend fun getNoteById(id: Long): NoteEntity? = noteDao.getNoteById(id)

  fun getFavoriteNotes(): Flow<List<NoteEntity>> = noteDao.getFavoriteNotes()

  fun getNotesByCategory(category: String): Flow<List<NoteEntity>> =
    noteDao.getNotesByCategory(category)

  fun searchNotes(searchQuery: String): Flow<List<NoteEntity>> =
    noteDao.searchNotes(searchQuery)

  fun getAllCategories(): Flow<List<String>> = noteDao.getAllCategories()

  suspend fun insertNote(noteEntity: NoteEntity): Long {
    val currentTime = Date()
    val noteToInsert = noteEntity.copy(
      createdAt = currentTime,
      updatedAt = currentTime,
    )
    return noteDao.insertNote(noteToInsert)
  }

  suspend fun updateNote(noteEntity: NoteEntity) {
    val updatedNote = noteEntity.copy(updatedAt = Date())
    noteDao.updateNote(updatedNote)
  }

  suspend fun deleteNote(noteEntity: NoteEntity) = noteDao.deleteNote(noteEntity)

  suspend fun deleteNoteById(id: Long) = noteDao.deleteNoteById(id)

  suspend fun deleteAllNotes() = noteDao.deleteAllNotes()

  suspend fun updateFavoriteStatus(id: Long, isFavorite: Boolean) =
    noteDao.updateFavoriteStatus(id, isFavorite)
}