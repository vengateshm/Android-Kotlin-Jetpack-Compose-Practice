package dev.vengateshm.compose_material3.testing.mvvm_local_db.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo_table")
data class ToDo(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    val title: String,
    val description: String,
    val isCompleted: Boolean
)
