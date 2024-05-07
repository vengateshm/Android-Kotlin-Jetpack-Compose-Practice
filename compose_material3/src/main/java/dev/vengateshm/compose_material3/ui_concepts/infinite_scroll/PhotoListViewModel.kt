package dev.vengateshm.compose_material3.ui_concepts.infinite_scroll

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class PhotoListViewModel(
    private val photoRepo: PhotoRepo
) : ViewModel() {

    var offset = 0

    val photoList = mutableStateListOf<PhotoItem>()

    init {
        getPhotos()
    }

    fun loadMoreItems() {
        offset += 10
        getPhotos()
    }

    private fun getPhotos() {
        viewModelScope.launch {
            try {
                val photos = photoRepo.getPhotos(offset)
                photoList.addAll(photos)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}

class PhotoListViewModelFactory(private val photoRepo: PhotoRepo) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PhotoListViewModel::class.java)) {
            @Suppress("unchecked_cast")
            return PhotoListViewModel(photoRepo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}