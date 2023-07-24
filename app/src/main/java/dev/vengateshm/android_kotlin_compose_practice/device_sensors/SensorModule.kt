package dev.vengateshm.android_kotlin_compose_practice.device_sensors

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SensorModule {
    @Singleton
    @Provides
    @Named("light")
    fun provideLightSensor(app: Application): MeasurableSensor {
        return LightSensor(app)
    }

    @Singleton
    @Provides
    @Named("step")
    fun provideStepDetectSensor(app: Application): MeasurableSensor {
        return StepDetectorSensor(app)
    }

    @Singleton
    @Provides
    @Named("orientation")
    fun provideScreenOrientationSensor(app: Application): MeasurableSensor {
        return ScreenOrientationSensor(app)
    }
}