package dev.vengateshm.compose_material3.di.koin

import org.koin.core.qualifier.named
import org.koin.dsl.module

val koinSampleModule = module {
    single<KoinSampleRepo>(qualifier = named("v1")) { KoinSampleRepoV1() }
    single<KoinSampleRepo>(qualifier = named("v2")) { KoinSampleRepoV2() }
}