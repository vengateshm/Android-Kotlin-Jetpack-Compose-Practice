package dev.vengateshm.android_kotlin_compose_practice.graph_ql_app.rickandmorty_api.ui.screen.character_list

import dev.vengateshm.android_kotlin_compose_practice.graph_ql_app.rickandmorty_api.domain.model.SimpleCharacter

data class CharacterListState(
    val isLoading: Boolean = false,
    val characterList: List<SimpleCharacter> = emptyList(),
)
