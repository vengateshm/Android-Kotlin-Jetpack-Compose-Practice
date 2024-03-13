package dev.vengateshm.compose_material3.lru_cache

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class CharacterViewModel(private val repo: CharacterRepo = CharacterRepoImpl(ApiClient)) :
    ViewModel() {
    var isLoading by mutableStateOf(false)
    var character by mutableStateOf<Character?>(null)

    fun getCharacter(id: String) {
        viewModelScope.launch {
            isLoading = true
            val result = repo.getCharacter(id)
            isLoading = false
            character = result
        }
    }
}