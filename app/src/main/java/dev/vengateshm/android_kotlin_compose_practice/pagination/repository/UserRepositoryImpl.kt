package dev.vengateshm.android_kotlin_compose_practice.pagination.repository

import dev.vengateshm.android_kotlin_compose_practice.pagination.network.UserApi
import dev.vengateshm.android_kotlin_compose_practice.pagination.network.UsersResponse
import kotlinx.coroutines.delay

class UserRepositoryImpl(
    private val api: UserApi,
) : UserRepository {
    override suspend fun getUsers(page: Int, limit: Int): UsersResponse {
        delay(3000L)
        return api.getUsers(page, limit)
    }
}