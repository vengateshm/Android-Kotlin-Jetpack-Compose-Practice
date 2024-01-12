package dev.vengateshm.android_kotlin_compose_practice.graph_ql_app.rickandmorty_api.domain.usecase

import dev.vengateshm.android_kotlin_compose_practice.graph_ql_app.rickandmorty_api.domain.model.SimpleCharacter
import dev.vengateshm.android_kotlin_compose_practice.graph_ql_app.rickandmorty_api.domain.repository.RickAndMortyApiRepository

class GetCharactersUseCase(private val repository: RickAndMortyApiRepository) {
    suspend operator fun invoke(): List<SimpleCharacter> {
        return repository.getCharacterList()
    }
}