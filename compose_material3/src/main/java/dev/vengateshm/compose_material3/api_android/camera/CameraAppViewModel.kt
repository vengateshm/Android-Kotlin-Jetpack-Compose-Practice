package dev.vengateshm.compose_material3.api_android.camera

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class CameraAppViewModel : ViewModel() {
    private val _images = MutableStateFlow<List<Bitmap>>(emptyList())
    val images = _images.asStateFlow()

    fun onTookPhoto(image: Bitmap) {
        _images.value += image
    }
}