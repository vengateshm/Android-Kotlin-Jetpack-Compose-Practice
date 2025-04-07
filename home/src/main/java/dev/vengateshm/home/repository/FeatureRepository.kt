package dev.vengateshm.home.repository

import android.content.Context
import dev.vengateshm.home.model.Feature

interface FeatureRepository {
    suspend fun getFeatures(): List<Feature>

    companion object {
        fun create(context: Context): FeatureRepository {
            return FeatureRepositoryImpl(context)
        }
    }
}