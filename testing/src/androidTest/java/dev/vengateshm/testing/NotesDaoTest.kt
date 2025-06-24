package dev.vengateshm.testing

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dev.vengateshm.testing.room_hilt.data.dao.NoteDao
import dev.vengateshm.testing.room_hilt.data.database.NotesDatabase
import dev.vengateshm.testing.room_hilt.data.entities.NoteEntity
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.Date
import javax.inject.Inject

@HiltAndroidTest
@SmallTest
class NotesDaoTest {

  @get:Rule
  val hiltRule = HiltAndroidRule(this)

  @get:Rule
  val instantTaskExecutorRule = InstantTaskExecutorRule()

  @Inject
  lateinit var database: NotesDatabase
  private lateinit var dao: NoteDao

  @Before
  fun setup() {
    hiltRule.inject()
    dao = database.noteDao()
  }

  @Test
  fun getAllNotesFromEmptyDb_returnsEmptyList() = runTest {
    assertThat(dao.getAllNotes().isEmpty())
  }

  @Test
  fun getAllNotesFromNonEmptyDb_returnsNonEmptyList() = runTest {
    for (i in 1..5) {
      dao.insertNote(
        NoteEntity(
          title = "Title $i",
          content = "Description $i",
          createdAt = Date(),
          updatedAt = Date(),
        ),
      )
    }
    assertThat(dao.getAllNotes().isNotEmpty())
  }

  @Test
  fun insertNote_returnsInsertedNote() = runTest {
    val note = NoteEntity(
      id = 1,
      title = "Title",
      content = "Description",
      createdAt = Date(),
      updatedAt = Date(),
    )
    dao.insertNote(note)
    val notes = dao.getAllNotes()
    assertThat(notes).contains(note)
  }

  @Test
  fun deleteNote_returnsEmptyList() = runTest {
    val note = NoteEntity(
      title = "Title",
      content = "Description",
      createdAt = Date(),
      updatedAt = Date(),
    )
    dao.insertNote(note)
    dao.deleteNote(note)
    val notes = dao.getAllNotes()
    assertThat(notes).doesNotContain(note)
  }

  @After
  fun teardown() {
    database.close()
  }
}