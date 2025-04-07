package dev.vengateshm.home.repository

import android.content.Context
import dev.vengateshm.home.model.Feature

class FeatureRepositoryImpl(private val context: Context) : FeatureRepository {
    override suspend fun getFeatures(): List<Feature> {
        return listOf(
            Feature(1, "Book Now", "ic_book"),
            Feature(2, "Track Order", "ic_track"),
        )
    }
}