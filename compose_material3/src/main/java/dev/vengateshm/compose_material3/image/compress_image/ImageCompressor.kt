package dev.vengateshm.compose_material3.image.compress_image

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.isActive
import kotlinx.coroutines.withContext
import java.io.ByteArrayOutputStream
import kotlin.math.roundToInt

class ImageCompressor {
    suspend fun compressImage(
        context: Context,
        contentUri: Uri,
        compressionThreshold: Long,
    ): ByteArray? {
        return withContext(Dispatchers.IO) {
            val inputBytes = context.contentResolver
                .openInputStream(contentUri)
                ?.use { inputStream ->
                    inputStream.readBytes()
                } ?: return@withContext null
            ensureActive()
            withContext(Dispatchers.Default) {
                val bitmap = BitmapFactory.decodeByteArray(inputBytes, 0, inputBytes.size)
                ensureActive()
                val mimeType = context.contentResolver.getType(contentUri)

                val compressFormat = when (mimeType) {
                    "image/png" -> Bitmap.CompressFormat.PNG
                    "image/jpeg" -> Bitmap.CompressFormat.JPEG
                    "image/webp" -> if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                        Bitmap.CompressFormat.WEBP_LOSSLESS
                    } else {
                        Bitmap.CompressFormat.WEBP
                    }

                    else -> Bitmap.CompressFormat.JPEG
                }

                var outputByteArray: ByteArray
                var quality = 100
                do {
                    ByteArrayOutputStream().use { outputStream ->
                        bitmap.compress(compressFormat, quality, outputStream)
                        outputByteArray = outputStream.toByteArray()
                        quality -= (quality * 0.1).roundToInt()
                    }
                } while (isActive && outputByteArray.size > compressionThreshold && quality > 5 && compressFormat != Bitmap.CompressFormat.PNG)

                outputByteArray
            }
        }
    }
}