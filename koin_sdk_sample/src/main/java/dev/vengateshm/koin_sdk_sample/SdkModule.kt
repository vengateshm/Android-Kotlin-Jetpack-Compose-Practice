package dev.vengateshm.koin_sdk_sample

import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val sdkModules = module {
  single<MySdkLogger> { MySdkLoggerImpl() }
  single<MySdkNetworkService> { MySdkNetworkServiceImpl() }
  factory { MySdkManager(get(), get(), get()) }
  single<MySdkDatabase> { MySdkDatabase(get()) }

  singleOf<ElectricityProvider>(::Reliance)
  factoryOf(::CoffeeBeans)
}