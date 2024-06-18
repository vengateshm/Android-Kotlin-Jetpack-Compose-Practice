package dev.vengateshm.compose_material3.kotest

import io.kotest.core.config.AbstractProjectConfig
import kotlin.time.Duration.Companion.minutes

object KotestConfig : AbstractProjectConfig() {
    override val timeout = 10.minutes
}