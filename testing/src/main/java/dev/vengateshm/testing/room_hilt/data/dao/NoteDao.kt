package dev.vengateshm.testing.room_hilt.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import dev.vengateshm.testing.room_hilt.data.entities.NoteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
  @Query("SELECT * FROM notes ORDER BY updatedAt DESC")
  suspend fun getAllNotes(): List<NoteEntity>

  @Query("SELECT * FROM notes WHERE id = :id")
  suspend fun getNoteById(id: Long): NoteEntity?

  @Query("SELECT * FROM notes WHERE isFavorite = 1 ORDER BY updatedAt DESC")
  fun getFavoriteNotes(): Flow<List<NoteEntity>>

  @Query("SELECT * FROM notes WHERE category = :category ORDER BY updatedAt DESC")
  fun getNotesByCategory(category: String): Flow<List<NoteEntity>>

  @Query("SELECT * FROM notes WHERE title LIKE '%' || :searchQuery || '%' OR content LIKE '%' || :searchQuery || '%' ORDER BY updatedAt DESC")
  fun searchNotes(searchQuery: String): Flow<List<NoteEntity>>

  @Query("SELECT DISTINCT category FROM notes ORDER BY category ASC")
  fun getAllCategories(): Flow<List<String>>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertNote(noteEntity: NoteEntity): Long

  @Update
  suspend fun updateNote(noteEntity: NoteEntity)

  @Delete
  suspend fun deleteNote(noteEntity: NoteEntity)

  @Query("DELETE FROM notes WHERE id = :id")
  suspend fun deleteNoteById(id: Long)

  @Query("DELETE FROM notes")
  suspend fun deleteAllNotes()

  @Query("UPDATE notes SET isFavorite = :isFavorite WHERE id = :id")
  suspend fun updateFavoriteStatus(id: Long, isFavorite: Boolean)
}