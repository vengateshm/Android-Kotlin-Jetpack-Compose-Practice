package dev.vengateshm.compose_material3.di.koin

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.qualifier.qualifier

class KoinSampleUseCase : KoinComponent {
    private val repo1: KoinSampleRepo by inject(qualifier("v1"))
    private val repo2: KoinSampleRepo by inject(qualifier("v2"))

    operator fun invoke(message: String) {
        repo1.foo(message)
        repo2.foo(message)
    }
}