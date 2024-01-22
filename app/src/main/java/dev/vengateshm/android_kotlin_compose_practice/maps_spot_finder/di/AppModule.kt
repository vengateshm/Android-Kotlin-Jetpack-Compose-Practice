package dev.vengateshm.android_kotlin_compose_practice.maps_spot_finder.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.vengateshm.android_kotlin_compose_practice.maps_spot_finder.data.ParkingSpotDatabase
import dev.vengateshm.android_kotlin_compose_practice.maps_spot_finder.data.ParkingSpotRepositoryImpl
import dev.vengateshm.android_kotlin_compose_practice.maps_spot_finder.domain.repository.ParkingSpotRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideParkingSpotDatabase(app: Application): ParkingSpotDatabase {
        return Room.databaseBuilder(
            app,
            ParkingSpotDatabase::class.java,
            "parking_spots.db",
        )
            .build()
    }

    @Singleton
    @Provides
    fun provideParkingSpotRepository(db: ParkingSpotDatabase): ParkingSpotRepository {
        return ParkingSpotRepositoryImpl(db.parkingSpotDao)
    }
}
