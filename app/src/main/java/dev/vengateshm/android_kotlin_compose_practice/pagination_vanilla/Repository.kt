package dev.vengateshm.android_kotlin_compose_practice.pagination_vanilla

import kotlinx.coroutines.delay

class Repository {
    private val remoteDataSource =
        (1..100).map {
            ListItem(title = "Title $it", description = "Description $it")
        }

    suspend fun getItems(
        page: Int,
        pageSize: Int,
    ): Result<List<ListItem>> {
        delay(2000L)
        val startIndex = page * pageSize // 0*10, 1*10
        return if (startIndex + pageSize <= remoteDataSource.size) {
            Result.success(remoteDataSource.slice(startIndex until startIndex + pageSize))
        } else {
            Result.success(emptyList())
        }
    }
}
