package dev.vengateshm.compose_material3.api_kotlin.coroutines

import android.content.Context
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.delay
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File

suspend fun createMyTempFile(context: Context) {
    val file = File(context.filesDir, "my-temp-file.txt")
    try {
        writeContentsToFile(file)
    } finally {
        // When one suspending function throws an exception,
        // all the coroutines that were launched in the scope of that coroutine will be cancelled.
        println("In finally block")
        //deleteFile(file)
        withContext(NonCancellable) {
            deleteFile(file)
        }
    }
}

suspend fun writeContentsToFile(file: File) {
    withContext(Dispatchers.IO) {
        repeat(100_000) {
            file.appendText("Hello world")
            ensureActive() // This will throw CancellationException if the coroutine is cancelled.
        }
    }
}

suspend fun deleteFile(file: File) {
    withContext(Dispatchers.IO) {
        file.delete()
        println("File deleted")
    }
}

fun coroutinesSecrets2(coroutineScope: CoroutineScope, context: Context) {
    coroutineScope.launch {
        val job = launch { createMyTempFile(context.applicationContext) }
        delay(50)
        job.cancel()
    }
}