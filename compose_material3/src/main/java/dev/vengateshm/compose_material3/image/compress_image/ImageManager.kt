package dev.vengateshm.compose_material3.image.compress_image

import android.content.Context
import android.net.Uri
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ImageManager(
    private val context: Context,
) {
    suspend fun saveImage(
        contentUri: Uri,
        fileName: String,
    ) {
        withContext(Dispatchers.IO) {
            context.contentResolver
                .openInputStream(contentUri)
                ?.use { inputStream ->
                    context.openFileOutput(fileName, Context.MODE_PRIVATE)
                        ?.use { outputStream ->
                            inputStream.copyTo(outputStream)
                        }
                }
        }
    }

    suspend fun saveImage(
        byteArray: ByteArray,
        fileName: String,
    ) {
        withContext(Dispatchers.IO) {
            context.openFileOutput(fileName, Context.MODE_PRIVATE)
                ?.use { outputStream ->
                    outputStream.write(byteArray)
                }
        }
    }
}