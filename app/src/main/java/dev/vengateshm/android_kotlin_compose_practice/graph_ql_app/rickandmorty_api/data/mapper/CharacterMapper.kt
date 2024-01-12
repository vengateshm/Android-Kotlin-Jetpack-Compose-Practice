package dev.vengateshm.android_kotlin_compose_practice.graph_ql_app.rickandmorty_api.data.mapper

import dev.vengateshm.android_kotlin_compose_practice.CharacterQuery
import dev.vengateshm.android_kotlin_compose_practice.CharactersQuery
import dev.vengateshm.android_kotlin_compose_practice.graph_ql_app.rickandmorty_api.domain.model.Character
import dev.vengateshm.android_kotlin_compose_practice.graph_ql_app.rickandmorty_api.domain.model.SimpleCharacter

fun CharactersQuery.Characters.toCharacterList(): List<SimpleCharacter> =
    this.results?.mapNotNull { result ->
        SimpleCharacter(
            id = result?.id.orEmpty(),
            name = result?.name.orEmpty(),
            image = result?.image.orEmpty(),
            species = result?.species.orEmpty(),
        )
    }.orEmpty()

fun CharacterQuery.Character.toCharacter() : Character =
    Character(
        id = this.id.orEmpty(),
        name = this.name.orEmpty(),
        image = this.image.orEmpty(),
        species = this.species.orEmpty(),
        gender = this.gender.orEmpty(),
    )