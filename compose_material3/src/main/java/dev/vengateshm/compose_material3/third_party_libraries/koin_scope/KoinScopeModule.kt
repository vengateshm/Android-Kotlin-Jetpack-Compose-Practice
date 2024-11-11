package dev.vengateshm.compose_material3.third_party_libraries.koin_scope

import org.koin.core.qualifier.named
import org.koin.dsl.module

val koinScopeModule = module {
    scope(named("LoginLogoutScope")) {
        scoped { LandingRepository() }
    }
}