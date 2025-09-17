package dev.vengateshm.koin_sdk_sample

import android.content.Context
import org.koin.core.component.get
import org.koin.dsl.module

object MySdk {
  private lateinit var sdkKoinComponent: SdkKoinComponent

  fun init(context: Context) {
    val appContext = context.applicationContext

    // Define a module that provides the application context
    val contextModule = module {
      single { appContext }
    }

    MySdkKoinContext.koin.loadModules(listOf(contextModule, sdkModules))

    // Create a component to hold our SDK's root dependencies
    sdkKoinComponent = object : SdkKoinComponent {}
  }

  fun doSomething() {
    // Access the MySdkManager from the isolated context
    val manager: MySdkManager = sdkKoinComponent.get()
    manager.performAction()
  }
}