package dev.vengateshm.compose_material3.di.hilt

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoMap
import dagger.multibindings.IntoSet
import dagger.multibindings.StringKey
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

    @Provides
    fun provideWheel(): Wheel {
        return Wheel(name = "App module wheel")
    }


    @IntoSet
    @Provides
    fun provideString1(): String {
        return "String 1"
    }

    @IntoSet
    @Provides
    fun provideString2(): String {
        return "String 2"
    }


    @IntoMap
    @StringKey("S1")
    @Provides
    fun provideString1Map(): String {
        return "String 1"
    }

    @IntoMap
    @StringKey("S2")
    @Provides
    fun provideString2Map(): String {
        return "String 2"
    }
}