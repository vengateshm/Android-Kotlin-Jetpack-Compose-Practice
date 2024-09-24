package dev.vengateshm.compose_material3.api_android.read_external_files.api_35

import android.Manifest
import android.content.ContentUris
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.provider.MediaStore
import androidx.core.content.ContextCompat

class MediaStoreReader(
    private val context: Context,
) {
    fun getMediaFiles(): List<MediaFile> {
        // Safe check for lower devices
        val skipQuery = if (Build.VERSION.SDK_INT <= 32) {
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.READ_EXTERNAL_STORAGE,
            ) != PackageManager.PERMISSION_GRANTED
        } else false
        if (skipQuery) return emptyList()
        val mediaFiles = mutableListOf<MediaFile>()

        val queryUri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            MediaStore.Files.getContentUri(MediaStore.VOLUME_EXTERNAL)
        } else {
            MediaStore.Files.getContentUri("external")
        }

        val projection = arrayOf(
            MediaStore.Files.FileColumns._ID,
            MediaStore.Files.FileColumns.DISPLAY_NAME,
            MediaStore.Files.FileColumns.MIME_TYPE,
        )

        context.contentResolver.query(
            queryUri,
            projection,
            null,
            null,
            null,
        )?.use { cursor ->
            val idCol = cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns._ID)
            val displayNameCol =
                cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DISPLAY_NAME)
            val mimeTypeCol = cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.MIME_TYPE)
            while (cursor.moveToNext()) {
                val id = cursor.getLong(idCol)
                val name = cursor.getString(displayNameCol)
                val mimeType = cursor.getString(mimeTypeCol)

                if (name != null && mimeType != null) {
                    val contentUri = ContentUris.withAppendedId(queryUri, id)
                    val medaType = when {
                        mimeType.startsWith("audio/") -> MediaType.AUDIO
                        mimeType.startsWith("video/") -> MediaType.VIDEO
                        else -> MediaType.IMAGE
                    }
                    mediaFiles.add(
                        MediaFile(
                            uri = contentUri,
                            name = name,
                            type = medaType,
                        ),
                    )
                }
            }
        }

        return mediaFiles.toList()
    }
}