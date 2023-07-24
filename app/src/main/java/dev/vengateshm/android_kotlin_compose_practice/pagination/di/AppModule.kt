package dev.vengateshm.android_kotlin_compose_practice.pagination.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.vengateshm.android_kotlin_compose_practice.pagination.network.UserApi
import dev.vengateshm.android_kotlin_compose_practice.pagination.repository.UserRepository
import dev.vengateshm.android_kotlin_compose_practice.pagination.repository.UserRepositoryImpl

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    fun provideUsersApi(): UserApi = UserApi()

    @Provides
    fun provideUserRepository(api: UserApi): UserRepository = UserRepositoryImpl(api)
}