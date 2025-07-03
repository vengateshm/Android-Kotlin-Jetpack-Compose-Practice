package dev.vengateshm.kotlin_practice.coroutines

import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

interface News

fun requestNews(onSuccess: (List<News>) -> Unit, onError: (Throwable) -> Unit) {

}

suspend fun getNews(): List<News> = suspendCancellableCoroutine { cont ->
  requestNews(
    onSuccess = {
      cont.resume(it)
    },
    onError = {
      cont.resumeWithException(it)
    },
  )
  cont.invokeOnCancellation {
    // cancel the request
  }
}