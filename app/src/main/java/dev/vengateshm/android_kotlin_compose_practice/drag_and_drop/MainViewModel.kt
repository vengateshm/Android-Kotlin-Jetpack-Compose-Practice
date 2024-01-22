package dev.vengateshm.android_kotlin_compose_practice.drag_and_drop

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    var isCurrentlyDragging by mutableStateOf(false)
        private set

    var items by mutableStateOf(emptyList<Person>())
        private set

    var addedPersons = mutableStateListOf<Person>()
        private set

    init {
        items =
            listOf(
                Person("Mike", "1", Color.Gray),
                Person("Larry", "2", Color.Blue),
                Person("Samantha", "3", Color.Green),
            )
    }

    fun startDragging() {
        isCurrentlyDragging = true
    }

    fun stopDragging() {
        isCurrentlyDragging = false
    }

    fun addPerson(person: Person) {
        addedPersons.add(person)
    }
}
