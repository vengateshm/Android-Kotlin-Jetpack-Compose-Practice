package dev.vengateshm.android_kotlin_compose_practice.graph_ql_app.rickandmorty_api.ui.screen.character_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.vengateshm.android_kotlin_compose_practice.graph_ql_app.rickandmorty_api.data.repository.ApolloRickAndMortyRepository
import dev.vengateshm.android_kotlin_compose_practice.graph_ql_app.rickandmorty_api.domain.usecase.GetCharactersUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CharacterListViewModel : ViewModel() {

    private val repository = ApolloRickAndMortyRepository()
    private val getCharactersUseCase = GetCharactersUseCase(repository)

    private val _state = MutableStateFlow(CharacterListState())
    val state = _state.asStateFlow()

    init {
        _state.update {
            it.copy(isLoading = true)
        }

        viewModelScope.launch {
            val result = getCharactersUseCase()
            _state.update {
                it.copy(isLoading = false, characterList = result)
            }
        }
    }
}