package dev.vengateshm.android_kotlin_compose_practice.pagination.pagingSource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import dev.vengateshm.android_kotlin_compose_practice.pagination.network.User
import dev.vengateshm.android_kotlin_compose_practice.pagination.repository.UserRepository

class UserDataSource(
    private val repository: UserRepository,
) : PagingSource<Int, User>() {
    override fun getRefreshKey(state: PagingState<Int, User>): Int? {
        return state.anchorPosition?.let { position ->
            val page = state.closestPageToPosition(position)
            page?.prevKey?.minus(1) ?: page?.nextKey?.plus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        return try {
            val page = params.key ?: 1
            val response = repository.getUsers(page, 10)
            LoadResult.Page(
                data = response.users,
                prevKey = null,
                nextKey = if (response.users.isEmpty()) null else response.page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}