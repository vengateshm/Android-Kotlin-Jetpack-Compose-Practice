package dev.vengateshm.kotlin_practice.design

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.concurrent.ConcurrentHashMap

fun main() {
    val cache = CacheManager<String>()
    cache.put("user_1", "John Doe")
    cache.put("user_2", "Bob Smith")
    runBlocking {
        println(cache.get("user_1"))
        println(cache.get("user_2"))
        delay(6000L)
        println(cache.get("user_1"))
        println(cache.get("user_2"))
        cache.stopEviction()
    }
}

const val CACHE_EXPIRATION = 5000L

class CacheItem<T>(val item: T, val timestamp: Long)

class CacheManager<T> {
    private val cache = ConcurrentHashMap<String, CacheItem<T>>()
    private val evictionJob: Job

    init {
        evictionJob = CoroutineScope(Dispatchers.Default).launch {
            while (isActive) {
                delay(5000L)
                removeEntries()
            }
        }
    }

    fun put(key: String, item: T) {
        cache[key] = CacheItem(item, System.currentTimeMillis())
    }

    fun get(key: String): T? {
        val currentTime = System.currentTimeMillis()
        return cache.compute(key) { _, item ->
            item?.takeIf { currentTime - item.timestamp < CACHE_EXPIRATION }
        }?.item
    }

    fun removeEntries() {
        val currentTime = System.currentTimeMillis()
        cache.entries.removeIf { (_, item) ->
            currentTime - item.timestamp > CACHE_EXPIRATION
        }
    }

    fun stopEviction() {
        evictionJob.cancel()
    }
}