package dev.vengateshm.compose_material3.di.hilt

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
object ViewModelModule {
    @Provides
    fun provideTransmission(): Transmission {
        return Transmission(name = "Viewmodel module transmission")
    }
}