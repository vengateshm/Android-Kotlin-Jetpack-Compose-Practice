package dev.vengateshm.home.repository

import android.content.Context
import dev.vengateshm.home.model.Banner

interface BannerRepository {
    suspend fun getBanners(): List<Banner>

    companion object {
        fun create(context: Context): BannerRepository {
            return BannerRepositoryImpl(context)
        }
    }
}
