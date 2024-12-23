package dev.vengateshm.compose_material3.di.koin

import org.koin.ksp.generated.module

val koinDiModules = listOf(
    dataModule, domainModule, uiModule, sessionModule, userModule, AnnotatedModule().module,
)