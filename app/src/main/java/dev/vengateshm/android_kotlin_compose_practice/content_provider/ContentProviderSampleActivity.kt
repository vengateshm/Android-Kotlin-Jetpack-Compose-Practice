package dev.vengateshm.android_kotlin_compose_practice.content_provider

import android.app.Activity
import android.content.ContentUris
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import coil.compose.AsyncImage
import java.util.Calendar

class ContentProviderSampleActivity : ComponentActivity() {
    private val viewModel = viewModels<ContentProviderViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            val permission = android.Manifest.permission.READ_MEDIA_IMAGES
            val isGranted =
                ContextCompat.checkSelfPermission(
                    this,
                    permission,
                ) == PackageManager.PERMISSION_GRANTED
            if (!isGranted) {
                // Request the permission.
                ActivityCompat.requestPermissions(
                    this as Activity,
                    arrayOf(permission),
                    115,
                )
            }
        } else {
            val permission = android.Manifest.permission.READ_EXTERNAL_STORAGE
            val isGranted =
                ContextCompat.checkSelfPermission(
                    this,
                    permission,
                ) == PackageManager.PERMISSION_GRANTED
            if (!isGranted) {
                // Request the permission.
                ActivityCompat.requestPermissions(
                    this as Activity,
                    arrayOf(permission),
                    115,
                )
            }
        }

        // Columns
        val projection =
            arrayOf(
                MediaStore.Images.Media._ID,
                MediaStore.Images.Media.DISPLAY_NAME,
                MediaStore.Images.Media.DATE_TAKEN,
            )
        // Query
        val selection = "${MediaStore.Images.Media.DATE_TAKEN} >= ?"
        val selectionArgs =
            arrayOf(
                Calendar.getInstance()
                    .apply {
                        add(Calendar.DAY_OF_YEAR, -30)
                    }.timeInMillis.toString(),
            )
        val sortOrder = "${MediaStore.Images.Media.DATE_TAKEN} DESC"
        contentResolver.query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            projection,
            selection,
            selectionArgs,
            sortOrder,
        )?.use { cursor ->
            val idColumn = cursor.getColumnIndex(MediaStore.Images.Media._ID)
            val nameColumn = cursor.getColumnIndex(MediaStore.Images.Media.DISPLAY_NAME)
            val dateTakenColumn = cursor.getColumnIndex(MediaStore.Images.Media.DATE_TAKEN)

            val images = mutableListOf<Image>()
            while (cursor.moveToNext()) {
                val id = cursor.getLong(idColumn)
                val name = cursor.getString(nameColumn)
                val dateTaken = cursor.getInt(dateTakenColumn)
                val uri =
                    ContentUris.withAppendedId(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        id,
                    )
                images.add(
                    Image(
                        id = id,
                        name = name,
                        dateTaken = dateTaken,
                        uri = uri,
                    ),
                )
            }
            viewModel.value.updateImages(images)
        }

        setContent {
            MaterialTheme {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    items(viewModel.value.images.value) { image ->
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                        ) {
                            AsyncImage(model = image.uri, contentDescription = null)
                            Text(
                                text = image.name,
                            )
                        }
                    }
                }
            }
        }
    }
}

data class Image(
    val id: Long,
    val name: String,
    val dateTaken: Int,
    val uri: Uri,
)
