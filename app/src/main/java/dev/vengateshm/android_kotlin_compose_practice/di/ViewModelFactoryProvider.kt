package dev.vengateshm.android_kotlin_compose_practice.di

import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@EntryPoint
@InstallIn(ActivityComponent::class)
interface ViewModelFactoryProvider {
    fun sampleViewModelProviderFactory(): SampleViewModel.Factory
}