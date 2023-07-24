package dev.vengateshm.android_kotlin_compose_practice.hilt_dependency_scopes

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
object SessionModule {

    @Provides
    @ViewModelScoped
    fun provideSessionTimer(): SessionTimer {
        return SessionTimer()
    }
}