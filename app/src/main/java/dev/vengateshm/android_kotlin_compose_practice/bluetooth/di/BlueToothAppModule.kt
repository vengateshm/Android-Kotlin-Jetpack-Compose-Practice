package dev.vengateshm.android_kotlin_compose_practice.bluetooth.di

import android.content.Context
import android.os.Build
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.vengateshm.android_kotlin_compose_practice.bluetooth.data.AndroidBluetoothController
import dev.vengateshm.android_kotlin_compose_practice.bluetooth.domain.BluetoothController
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object BlueToothAppModule {
    @Provides
    @Singleton
    fun provideBluetoothController(
        @ApplicationContext context: Context,
    ): BluetoothController {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            AndroidBluetoothController(context)
        } else {
            TODO("VERSION.SDK_INT < M")
        }
    }
}
