package dev.vengateshm.android_kotlin_compose_practice.graph_ql_app.rickandmorty_api.domain.usecase

import dev.vengateshm.android_kotlin_compose_practice.graph_ql_app.rickandmorty_api.domain.model.Character
import dev.vengateshm.android_kotlin_compose_practice.graph_ql_app.rickandmorty_api.domain.repository.RickAndMortyApiRepository

class GetCharacterByIdUseCase(private val repository: RickAndMortyApiRepository) {
    suspend operator fun invoke(id: String): Character? {
        return repository.getCharacter(id)
    }
}