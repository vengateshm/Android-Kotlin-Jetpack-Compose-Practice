package dev.vengateshm.compose_material3.di.hilt

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    @Provides
    @Singleton
    fun provideEngine(): Engine {
        return Engine(name = "App module engine")
    }

    @Provides
    @Singleton
    @Named("app_module_transmission")
    fun provideTransmission(): Transmission {
        return Transmission(name = "App module transmission")
    }
}