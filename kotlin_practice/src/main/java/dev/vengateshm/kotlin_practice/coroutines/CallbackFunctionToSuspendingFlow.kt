package dev.vengateshm.kotlin_practice.coroutines

import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlin.coroutines.cancellation.CancellationException

class CallBackBasedApi<T> {
  fun registerCallback(callback: CallBack<T>) {}
  fun unregisterCallback(callback: CallBack<T>) {}
  interface CallBack<T> {
    fun onNext(value: T) {}
    fun onApiError(throwable: Throwable) {}
    fun onCompleted() {}
  }
}

fun <T> flowFrom(api: CallBackBasedApi<T>): Flow<T> = callbackFlow {
  val callback = object : CallBackBasedApi.CallBack<T> {
    override fun onNext(value: T) {
      trySendBlocking(value)
    }

    override fun onApiError(throwable: Throwable) {
      cancel(CancellationException("API Error", throwable))
    }

    override fun onCompleted() {
      channel.close()
    }
  }
  api.registerCallback(callback)
  awaitClose { api.unregisterCallback(callback) }
}