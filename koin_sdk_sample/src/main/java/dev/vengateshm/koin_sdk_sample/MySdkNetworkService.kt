package dev.vengateshm.koin_sdk_sample

interface MySdkNetworkService {
  fun fetchUserData(): String
}

class MySdkNetworkServiceImpl : MySdkNetworkService {
  override fun fetchUserData(): String {
    return "Mock user data from SDK"
  }
}