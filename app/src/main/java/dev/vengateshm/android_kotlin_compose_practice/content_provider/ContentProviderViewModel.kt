package dev.vengateshm.android_kotlin_compose_practice.content_provider

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class ContentProviderViewModel : ViewModel() {
    var images = mutableStateOf(emptyList<Image>())
        private set

    fun updateImages(imageList: List<Image>) {
        images.value = imageList
    }
}