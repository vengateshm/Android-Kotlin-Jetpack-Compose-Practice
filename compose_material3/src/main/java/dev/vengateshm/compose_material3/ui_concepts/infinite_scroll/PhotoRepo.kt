package dev.vengateshm.compose_material3.ui_concepts.infinite_scroll

interface PhotoRepo {
    suspend fun getPhotos(offset: Int, limit: Int=10): List<PhotoItem>
}