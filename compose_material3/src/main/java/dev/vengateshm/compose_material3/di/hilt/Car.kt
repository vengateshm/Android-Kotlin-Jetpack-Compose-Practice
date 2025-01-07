package dev.vengateshm.compose_material3.di.hilt

import javax.inject.Inject
import javax.inject.Named

data class Car @Inject constructor(
    val engine: Engine,
    @Named("app_module_transmission") val transmission: Transmission,
)
