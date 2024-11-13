package dev.vengateshm.compose_material3.api_android.app_search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CoffeeViewModel(
    private val coffeeSearchManager: CoffeeSearchManager,
) : ViewModel() {
    private var _uiState = MutableStateFlow(CoffeeScreenUiState())
    val uiState = _uiState.asStateFlow()

    var searchJob: Job? = null

    init {
        viewModelScope.launch {
            coffeeSearchManager.initialize()
            (0..50).map {
                Coffee(
                    namespace = "coffee",
                    id = it.toString(),
                    score = it,
                    name = "Coffee $it",
                )
            }.also {
                coffeeSearchManager.addCoffeeList(it)
            }
        }
    }

    fun onSearchQueryChange(query: String) {
        _uiState.update {
            it.copy(searchQuery = query)
        }

        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(500L)
            val coffeeList = coffeeSearchManager.searchCoffee(query)
            _uiState.update {
                it.copy(coffeeList = coffeeList)
            }
        }
    }

    override fun onCleared() {
        coffeeSearchManager.closeSession()
        super.onCleared()
    }
}