package dev.vengateshm.compose_material3.lru_cache

interface CharacterRepo {
    suspend fun getCharacter(id:String): Character?
}