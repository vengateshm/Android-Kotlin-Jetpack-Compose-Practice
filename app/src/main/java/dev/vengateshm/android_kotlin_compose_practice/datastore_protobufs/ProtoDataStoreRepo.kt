package dev.vengateshm.android_kotlin_compose_practice.datastore_protobufs

import kotlinx.coroutines.flow.Flow

interface ProtoDataStoreRepo {
    suspend fun isDarkTheme(): Flow<Boolean>

    suspend fun saveTheme(isDark: Boolean)
}
