package dev.vengateshm.glance_app_widget.network

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object LiveScoreApiService {
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(LiveScoreApi.BASE_URL)
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder().setPrettyPrinting().create()
                )
            )
            .build()
    }

    fun getLiveScoreApi(): LiveScoreApi = retrofit.create(LiveScoreApi::class.java)
}
