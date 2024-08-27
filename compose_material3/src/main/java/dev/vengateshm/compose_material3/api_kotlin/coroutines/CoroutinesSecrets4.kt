package dev.vengateshm.compose_material3.api_kotlin.coroutines

import android.content.Context
import android.net.Uri
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.withContext
import java.io.ByteArrayOutputStream

class ImageReader(
    private val context: Context,
    private val dispatcherProvider: DispatcherProvider
) {
    suspend fun readImageFromUri(contentUri: Uri): ByteArray {
        return withContext(dispatcherProvider.io) {
            context.contentResolver.openInputStream(contentUri)?.use { inputStream ->
                //inputStream.readBytes() // For small images this is ok
                ByteArrayOutputStream().use { outputStream ->
                    var byte = inputStream.read()
                    while (byte != -1) {
                        outputStream.write(byte)
                        byte = inputStream.read()

                        ensureActive() // This will throw CancellationException if the coroutine is cancelled.
//                        yield()
//                        delay(10.milliseconds)
                    }
                    outputStream.toByteArray()
                }
            } ?: byteArrayOf()
        }
    }
}

interface DispatcherProvider {
    val main: CoroutineDispatcher
    val io: CoroutineDispatcher
    val default: CoroutineDispatcher
}

class StandardDispatcher : DispatcherProvider {
    override val main: CoroutineDispatcher
        get() = Dispatchers.Main
    override val io: CoroutineDispatcher
        get() = Dispatchers.IO
    override val default: CoroutineDispatcher
        get() = Dispatchers.Default
}