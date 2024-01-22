package dev.vengateshm.android_kotlin_compose_practice.search_functionality

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class SearchViewModel : ViewModel() {
    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    private val _persons = MutableStateFlow<List<Person>>(allPersons)

    @OptIn(FlowPreview::class)
    val persons =
        searchText
            .debounce(1000L)
            .onEach {
                _isSearching.update { true }
            }
            .combine(_persons) { text, persons ->
                delay(2000L)
                if (text.isEmpty()) {
                    persons
                } else {
                    persons.filter { it.doesMatchSearchQuery(text) }
                }
            }
            .onEach {
                _isSearching.update { false }
            }
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(5000),
                _persons.value,
            )

    fun onSearchTextChanged(text: String) {
        _searchText.value = text
    }
}

data class Person(
    val firstName: String,
    val lastName: String,
) {
    fun doesMatchSearchQuery(query: String): Boolean {
        val matchingCombinations =
            listOf(
                "$firstName$lastName",
                "$firstName $lastName",
                "${firstName.first()}${lastName.first()}",
                "${firstName.first()} ${lastName.first()}",
            )
        return matchingCombinations.any {
            it.contains(query, ignoreCase = true)
        }
    }
}

private val allPersons =
    listOf(
        Person(
            firstName = "Steve",
            lastName = "Jobs",
        ),
        Person(
            firstName = "Phillip",
            lastName = "Lackner",
        ),
        Person(
            firstName = "Jake",
            lastName = "Wharton",
        ),
        Person(
            firstName = "Amit",
            lastName = "Shekar",
        ),
    )
