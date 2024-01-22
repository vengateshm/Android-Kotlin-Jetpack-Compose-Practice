package dev.vengateshm.android_kotlin_compose_practice.pagination_vanilla

interface Paginator<Key, Item> {
    suspend fun loadNextItems()

    fun reset()
}
