package dev.vengateshm.testing.room_hilt.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import dev.vengateshm.testing.room_hilt.data.converters.DateConverter
import dev.vengateshm.testing.room_hilt.data.dao.NoteDao
import dev.vengateshm.testing.room_hilt.data.entities.NoteEntity

@Database(
  entities = [NoteEntity::class],
  version = 1,
  exportSchema = false,
)
@TypeConverters(DateConverter::class)
abstract class NotesDatabase : RoomDatabase() {
  abstract fun noteDao(): NoteDao

  companion object {
    @Volatile
    private var INSTANCE: NotesDatabase? = null

    fun getDatabase(context: Context): NotesDatabase {
      return INSTANCE ?: synchronized(this) {
        val instance = Room.databaseBuilder(
          context.applicationContext,
          NotesDatabase::class.java,
          "notes_database",
        )
          .fallbackToDestructiveMigration()
          .build()
        INSTANCE = instance
        instance
      }
    }
  }
}