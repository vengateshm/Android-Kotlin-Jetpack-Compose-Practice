package dev.vengateshm.android_kotlin_compose_practice.graph_ql_app.rickandmorty_api.domain.repository

import dev.vengateshm.android_kotlin_compose_practice.graph_ql_app.rickandmorty_api.domain.model.Character
import dev.vengateshm.android_kotlin_compose_practice.graph_ql_app.rickandmorty_api.domain.model.SimpleCharacter

interface RickAndMortyApiRepository {
    suspend fun getCharacterList(): List<SimpleCharacter>

    suspend fun getCharacter(id: String): Character?
}
