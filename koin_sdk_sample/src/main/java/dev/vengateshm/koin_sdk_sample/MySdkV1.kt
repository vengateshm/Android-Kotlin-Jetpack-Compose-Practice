package dev.vengateshm.koin_sdk_sample

import org.koin.core.component.inject

class MySdkV1 private constructor() : SdkKoinComponent {

  private val manager: MySdkManager by inject()
  private val coffeeBeans: CoffeeBeans by inject()
  private val electricityProvider: ElectricityProvider by inject()
  private val logger: MySdkLogger by inject()

  fun doSomething() {
    manager.performAction()
    electricityProvider.switchOn()
    logger.log("$coffeeBeans")
  }

  companion object {
    val INSTANCE: MySdkV1 by lazy { MySdkV1() }
  }
}