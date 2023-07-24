package dev.vengateshm.common_lib

import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface PreferenceProviderEntryPoint {
    fun preferenceProvider(): PreferenceProvider
}