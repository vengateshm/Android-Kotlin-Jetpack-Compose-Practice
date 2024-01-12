package dev.vengateshm.android_kotlin_compose_practice.graph_ql_app.rickandmorty_api.data.repository

import com.apollographql.apollo3.ApolloClient
import dev.vengateshm.android_kotlin_compose_practice.CharacterQuery
import dev.vengateshm.android_kotlin_compose_practice.CharactersQuery
import dev.vengateshm.android_kotlin_compose_practice.graph_ql_app.rickandmorty_api.data.mapper.toCharacter
import dev.vengateshm.android_kotlin_compose_practice.graph_ql_app.rickandmorty_api.data.mapper.toCharacterList
import dev.vengateshm.android_kotlin_compose_practice.graph_ql_app.rickandmorty_api.domain.model.Character
import dev.vengateshm.android_kotlin_compose_practice.graph_ql_app.rickandmorty_api.domain.model.SimpleCharacter
import dev.vengateshm.android_kotlin_compose_practice.graph_ql_app.rickandmorty_api.domain.repository.RickAndMortyApiRepository

class ApolloRickAndMortyRepository(
    private val apolloClient: ApolloClient = ApolloClient.Builder()
        .serverUrl("https://rickandmortyapi.com/graphql")
        .build()
) : RickAndMortyApiRepository {

    override suspend fun getCharacterList(): List<SimpleCharacter> {
        val data = try {
            val query = apolloClient.query(CharactersQuery())
            val result = query.execute()
            result.data
        } catch (e: Exception) {
            null
        }

        return data?.characters?.toCharacterList().orEmpty()
    }

    override suspend fun getCharacter(id: String): Character? =
        apolloClient
            .query(CharacterQuery(id))
            .execute()
            .data
            ?.character
            ?.toCharacter()
}