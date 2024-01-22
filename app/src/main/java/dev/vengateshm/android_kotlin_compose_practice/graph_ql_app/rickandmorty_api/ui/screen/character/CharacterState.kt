package dev.vengateshm.android_kotlin_compose_practice.graph_ql_app.rickandmorty_api.ui.screen.character

import dev.vengateshm.android_kotlin_compose_practice.graph_ql_app.rickandmorty_api.domain.model.Character

data class CharacterState(
    val isLoading: Boolean = false,
    val character: Character? = null,
)
