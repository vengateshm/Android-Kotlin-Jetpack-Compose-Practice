package dev.vengateshm.compose_material3.other_concepts.upload_file

import android.content.Context
import android.net.Uri
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.UUID

class FileReader(
    private val context: Context,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
) {
    suspend fun uriToFileInfo(contentUri: Uri): FileInfo {
        return withContext(dispatcher) {
            val bytes = context
                .contentResolver
                .openInputStream(contentUri)
                ?.use { inputStream ->
                    inputStream.readBytes()
                } ?: byteArrayOf()

            val fileName = UUID.randomUUID().toString()
            val mimeType = context.contentResolver.getType(contentUri) ?: ""
            FileInfo(fileName, mimeType, bytes)
        }
    }
}