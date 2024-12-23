package dev.vengateshm.compose_material3.di.koin

import dev.vengateshm.compose_material3.di.koin.domain.KoinDiUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { KoinDiUseCase(get()) }
}