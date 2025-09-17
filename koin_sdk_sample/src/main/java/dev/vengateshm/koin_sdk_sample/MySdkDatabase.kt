package dev.vengateshm.koin_sdk_sample

import android.content.Context

class MySdkDatabase(private val context: Context) {
  fun getDatabasePath(): String {
    return context.getDatabasePath("my_sdk.db").absolutePath
  }
}