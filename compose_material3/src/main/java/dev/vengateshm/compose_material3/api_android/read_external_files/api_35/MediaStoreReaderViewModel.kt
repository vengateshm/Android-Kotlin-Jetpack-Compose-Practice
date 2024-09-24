package dev.vengateshm.compose_material3.api_android.read_external_files.api_35

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class MediaStoreReaderViewModel(
    private val mediaStoreReader: MediaStoreReader,
) : ViewModel() {
    var files by mutableStateOf(listOf<MediaFile>())
        private set

    fun getFiles() {
        files = mediaStoreReader.getMediaFiles()
    }
}