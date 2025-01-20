package dev.vengateshm.android_kotlin_compose_practice.pagination_vanilla

class DefaultPaginator<Key, Item>(
    private val initialKey: Key,
    private val onLoadUpdated: (Boolean) -> Unit,
    private val onRequest: suspend (nextKey: Key) -> Result<List<Item>>,
    private val getNextKey: suspend (List<Item>) -> Key,
    private val onError: suspend (Throwable?) -> Unit,
    private val onSuccess: suspend (items: List<Item>, newKey: Key) -> Unit,
) : Paginator<Key, Item> {
    private var currentKey = initialKey
    private var isRequestInProgress = false

    override suspend fun loadNextItems() {
        if (isRequestInProgress) return
        isRequestInProgress = true
        onLoadUpdated(true)
        val result = onRequest(currentKey)
        isRequestInProgress = false
        val items =
            result.getOrElse {
                onError(it)
                onLoadUpdated(false)
                return
            }
        currentKey = getNextKey(items)
        onSuccess(items, currentKey)
        onLoadUpdated(false)
    }

    override fun reset() {
        currentKey = initialKey
    }
}
