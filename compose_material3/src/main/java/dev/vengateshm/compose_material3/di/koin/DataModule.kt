package dev.vengateshm.compose_material3.di.koin

import dev.vengateshm.compose_material3.di.koin.data.KoinDiRepo
import dev.vengateshm.compose_material3.di.koin.data.KoinDiRepoImpl
import org.koin.dsl.module

val dataModule = module {
    single<KoinDiRepo> { KoinDiRepoImpl() }
}