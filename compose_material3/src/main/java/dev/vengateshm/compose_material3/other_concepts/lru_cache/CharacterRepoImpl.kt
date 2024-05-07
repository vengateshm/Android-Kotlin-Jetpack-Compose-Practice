package dev.vengateshm.compose_material3.other_concepts.lru_cache

import io.ktor.client.call.body
import io.ktor.client.request.get

class CharacterRepoImpl(
    private val apiClient: ApiClient
) : CharacterRepo {
    override suspend fun getCharacter(id: String): Character? {
        return try {
            val character = CharacterLruCache.getCharacter(id)
            if (character == null) {
                val result = ApiClient.instance.get("https://rickandmortyapi.com/api/character/$id")
                    .body<Character>()
                CharacterLruCache.saveCharacter(id, result)
            }
            CharacterLruCache.getCharacter(id)
        } catch (e: Exception) {
            null
        }
    }
}