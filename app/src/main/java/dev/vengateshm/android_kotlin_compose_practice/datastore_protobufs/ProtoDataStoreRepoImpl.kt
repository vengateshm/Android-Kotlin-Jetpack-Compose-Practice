package dev.vengateshm.android_kotlin_compose_practice.datastore_protobufs

import androidx.datastore.core.DataStore
import dev.vengateshm.android_kotlin_compose_practice.ThemeStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

class ProtoDataStoreRepoImpl(private val protoDatastore: DataStore<dev.vengateshm.android_kotlin_compose_practice.ThemeStore>) :
    ProtoDataStoreRepo {
    override suspend fun isDarkTheme(): Flow<Boolean> {
        return protoDatastore.data
            .catch { e ->
                if (e is IOException) {
                    emit(dev.vengateshm.android_kotlin_compose_practice.ThemeStore.getDefaultInstance())
                } else {
                    throw e
                }
            }
            .map {
                it.isDark
            }
    }

    override suspend fun saveTheme(isDark: Boolean) {
        protoDatastore.updateData {
            it.toBuilder()
                .setIsDark(isDark)
                .build()
        }
    }
}
