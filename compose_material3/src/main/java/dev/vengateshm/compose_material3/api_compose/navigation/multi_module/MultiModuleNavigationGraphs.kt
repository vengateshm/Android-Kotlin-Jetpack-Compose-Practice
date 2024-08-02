package dev.vengateshm.compose_material3.api_compose.navigation.multi_module

import dev.vengateshm.samples.auth.navigation.AuthFeature
import dev.vengateshm.samples.home.navigation.HomeFeature

data class MultiModuleNavigationGraphs(
    val authFeature: AuthFeature,
    val homeFeature: HomeFeature
)
