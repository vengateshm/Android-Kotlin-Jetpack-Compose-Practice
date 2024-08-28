package dev.vengateshm.glance_app_widget.network

import retrofit2.http.GET

interface COVIDApi {
    @GET("summary")
    suspend fun getSummary(): dev.vengateshm.glance_app_widget.models.SummaryResponse

    companion object {
        const val BASE_URL = "https://api.covid19api.com/"
    }
}
