package dev.vengateshm.testing

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.vengateshm.testing.room_hilt.data.database.NotesDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TestAppModule {
  @Provides
  @Singleton
  fun provideNoteDb(application: Application): NotesDatabase {
    return Room.inMemoryDatabaseBuilder(
      application,
      NotesDatabase::class.java,
    ).build()
  }
}