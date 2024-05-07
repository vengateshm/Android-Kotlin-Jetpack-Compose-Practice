package dev.vengateshm.compose_material3.other_concepts.lru_cache

interface CharacterRepo {
    suspend fun getCharacter(id:String): Character?
}