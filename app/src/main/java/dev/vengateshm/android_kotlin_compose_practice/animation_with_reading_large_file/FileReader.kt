package dev.vengateshm.android_kotlin_compose_practice.animation_with_reading_large_file

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FileReader(private val context: Context) {
    suspend fun readFile(fileName: String): ByteArray {
        return withContext(Dispatchers.IO) {
            context.assets.open(fileName).use {
                it.readBytes()
            }
        }
    }
}