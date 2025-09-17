package dev.vengateshm.koin_sdk_sample

import org.koin.core.KoinApplication
import org.koin.dsl.koinApplication

internal object MySdkKoinContext {
  val koinApp: KoinApplication by lazy {
    // We use koinApplication() instead of startKoin() to avoid
    // registering this Koin context in the global context.
    koinApplication {
      // Loggers, properties, and other configurations can be set here.
    }
  }

  val koin = koinApp.koin
}