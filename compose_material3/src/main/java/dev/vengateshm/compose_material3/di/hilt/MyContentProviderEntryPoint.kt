package dev.vengateshm.compose_material3.di.hilt

import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@EntryPoint
interface MyContentProviderEntryPoint {
    fun getEngine(): Engine
}