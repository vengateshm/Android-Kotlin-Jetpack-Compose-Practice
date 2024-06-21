package dev.vengateshm.kotlin_practice.std_library_samples

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        val searchScreen = SearchScreen()
        launch {
            searchScreen.uiState.collect {
                println(it)
            }
        }
        launch {
            searchScreen.search()
        }
    }
}

class SearchScreen {
    val uiState: StateFlow<UiStatus>
        field = MutableStateFlow<UiStatus>(UiStatus.None)

    suspend fun search() {
        uiState.value = UiStatus.None
        delay(1000L)
        uiState.value = UiStatus.Loading
        delay(1000L)
        uiState.value = UiStatus.Success
    }
}

sealed interface UiStatus {
    data object None : UiStatus
    data object Loading : UiStatus
    data object Success : UiStatus
    data object Error : UiStatus
}