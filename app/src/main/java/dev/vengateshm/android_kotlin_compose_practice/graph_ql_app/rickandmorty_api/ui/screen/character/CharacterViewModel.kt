package dev.vengateshm.android_kotlin_compose_practice.graph_ql_app.rickandmorty_api.ui.screen.character

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.vengateshm.android_kotlin_compose_practice.graph_ql_app.rickandmorty_api.data.repository.ApolloRickAndMortyRepository
import dev.vengateshm.android_kotlin_compose_practice.graph_ql_app.rickandmorty_api.domain.usecase.GetCharacterByIdUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CharacterViewModel(
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val repository = ApolloRickAndMortyRepository()
    private val getCharacterByIdUseCase = GetCharacterByIdUseCase(repository)

    private val _state = MutableStateFlow(CharacterState())
    val state = _state.asStateFlow()

    init {
        savedStateHandle.get<String>("id")?.let { id ->
            _state.update {
                it.copy(isLoading = true)
            }

            viewModelScope.launch {
                val result = getCharacterByIdUseCase(id)
                _state.update {
                    it.copy(isLoading = false, character = result)
                }
            }
        }
    }
}
