package dev.vengateshm.koin_sdk_sample

interface MySdkLogger {
  fun log(message: String)
}

class MySdkLoggerImpl : MySdkLogger {
  override fun log(message: String) {
    println("MySdkLogger: $message")
  }
}