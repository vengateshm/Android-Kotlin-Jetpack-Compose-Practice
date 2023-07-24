package dev.vengateshm.glance_app_widget.network

import dev.vengateshm.glance_app_widget.models.LiveMatchesResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface LiveScoreApi {
    @GET("matches/v2/list-live")
    suspend fun getLiveMatches(
        @Query("Category") category: String = "cricket",
        @Header("X-Rapidapi-Host") host: String = "livescore6.p.rapidapi.com",
        @Header("X-Rapidapi-Key") key: String = "ada30abe7dmsh488fb7b629c9927p13e78cjsncdf31e6870d1",
    ): LiveMatchesResponse

    companion object {
        const val BASE_URL = "https://livescore6.p.rapidapi.com/"
    }
}