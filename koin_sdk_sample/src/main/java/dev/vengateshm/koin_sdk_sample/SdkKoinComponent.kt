package dev.vengateshm.koin_sdk_sample

import org.koin.core.Koin
import org.koin.core.component.KoinComponent

class SdkKoinComponent : KoinComponent {
  override fun getKoin(): Koin = MySdkKoinContext.koin
}