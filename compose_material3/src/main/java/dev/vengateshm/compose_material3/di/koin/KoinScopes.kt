package dev.vengateshm.compose_material3.di.koin

import org.koin.core.qualifier.named
import org.koin.dsl.module

data class SessionManager(
    val session: String,
)

val sessionModule = module {
    scope<KoinDiActivity> {
        scoped {
            SessionManager(session = "This is user session")
        }
    }
}


data class User(val userName: String)

val UserScope = named("UserScope")

val userModule = module {
    scope(UserScope) {
        scoped {
            User(userName = "Rockfeller")
        }
    }
}