package dev.vengateshm.compose_material3.ui_concepts.paging.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface BreweryApi {
    @GET("breweries")
    suspend fun getBreweries(
        @Query("page") page: Int,
        @Query("per_page") pageCount: Int
    ): List<BreweryDto>

    companion object {
        const val BASE_URL = "https://api.openbrewerydb.org/v1/"
    }
}