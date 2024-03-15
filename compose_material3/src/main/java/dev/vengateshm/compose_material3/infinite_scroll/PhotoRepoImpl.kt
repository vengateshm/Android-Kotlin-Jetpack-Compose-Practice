package dev.vengateshm.compose_material3.infinite_scroll

class PhotoRepoImpl(private val photoService: PhotoService) : PhotoRepo {
    override suspend fun getPhotos(offset: Int, limit: Int): List<PhotoItem> {
        return photoService.getPhotos(offset, limit).photos
    }
}