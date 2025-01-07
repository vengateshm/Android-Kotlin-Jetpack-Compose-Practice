package dev.vengateshm.compose_material3.di.hilt

import javax.inject.Inject

data class Car @Inject constructor(
    val engine: Engine,
    val transmission: Transmission,
)
