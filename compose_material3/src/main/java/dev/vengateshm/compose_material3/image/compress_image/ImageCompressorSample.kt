package dev.vengateshm.compose_material3.image.compress_image

import android.webkit.MimeTypeMap
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.launch

@Composable
fun ImageCompressorSample() {
    val context = LocalContext.current
    val imageCompressor = remember { ImageCompressor() }
    val imageManager = remember { ImageManager(context.applicationContext) }
    var compressedImage by remember {
        mutableStateOf<ByteArray?>(null)
    }
    val coroutineScope = rememberCoroutineScope()
    val photoPicker =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.PickVisualMedia()) { contentUri ->
            if (contentUri == null) {
                return@rememberLauncherForActivityResult
            }
            val mimeType = context.contentResolver.getType(contentUri)
            val extension = MimeTypeMap.getSingleton().getExtensionFromMimeType(mimeType)
            coroutineScope.launch {
                imageManager.saveImage(
                    contentUri = contentUri,
                    fileName = "uncompressed.$extension",
                )
            }
            coroutineScope.launch {
                compressedImage = imageCompressor.compressImage(
                    context = context,
                    contentUri = contentUri,
                    compressionThreshold = 200 * 1024L, // 200 Kb
                )
                imageManager.saveImage(
                    byteArray = compressedImage ?: return@launch,
                    fileName = "compressed.$extension",
                )
            }
        }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Button(
            onClick = {
                PickVisualMediaRequest(
                    mediaType = ActivityResultContracts.PickVisualMedia.ImageOnly,
                )
                    .also(photoPicker::launch)
            },
        ) {
            Text(text = "Pick an image")
        }
    }
}