package dev.vengateshm.home.repository

import android.content.Context
import dev.vengateshm.home.model.Banner

class BannerRepositoryImpl(private val context: Context) : BannerRepository {
    override suspend fun getBanners(): List<Banner> {
        return listOf(
            Banner(1, "https://example.com/banner1.png", "Welcome Offer"),
            Banner(2, "https://example.com/banner2.png", "Limited Sale"),
        )
    }
}