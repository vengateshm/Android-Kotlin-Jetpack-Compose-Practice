package dev.vengateshm.android_kotlin_compose_practice.pagination.repository

import dev.vengateshm.android_kotlin_compose_practice.pagination.network.UsersResponse

interface UserRepository {
    suspend fun getUsers(page: Int, limit: Int): UsersResponse
}