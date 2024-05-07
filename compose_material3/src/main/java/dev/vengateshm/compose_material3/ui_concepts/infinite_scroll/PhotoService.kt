package dev.vengateshm.compose_material3.ui_concepts.infinite_scroll

import retrofit2.http.GET
import retrofit2.http.Query

interface PhotoService {

    @GET("sample-data/photos")
    suspend fun getPhotos(@Query("offset") offset: Int, @Query("limit") limit: Int=10): PhotoListResponse
}