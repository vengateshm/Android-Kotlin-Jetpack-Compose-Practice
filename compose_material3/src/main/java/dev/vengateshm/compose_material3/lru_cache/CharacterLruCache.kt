package dev.vengateshm.compose_material3.lru_cache

import androidx.collection.LruCache

object CharacterLruCache {
    private val maxMemory = (Runtime.getRuntime().maxMemory() / 1024).toInt()
    private val cacheSize = maxMemory / 8
    private val cache = LruCache<String, Character>(maxSize = cacheSize)

    fun saveCharacter(id: String, character: Character) {
        cache.put(id, character)
    }

    fun getCharacter(id: String): Character? {
        return cache[id]
    }
}