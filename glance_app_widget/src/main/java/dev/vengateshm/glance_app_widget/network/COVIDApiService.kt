package dev.vengateshm.glance_app_widget.network

import com.google.gson.GsonBuilder
import dev.vengateshm.glance_app_widget.network.COVIDApi
import dev.vengateshm.glance_app_widget.network.COVIDApi.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object COVIDApiService {
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder().setPrettyPrinting().create()
                )
            )
            .build()
    }

    fun getCOVIDApi(): dev.vengateshm.glance_app_widget.network.COVIDApi {
        return dev.vengateshm.glance_app_widget.network.COVIDApiService.retrofit.create(dev.vengateshm.glance_app_widget.network.COVIDApi::class.java)
    }
}