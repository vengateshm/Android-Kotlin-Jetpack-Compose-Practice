package dev.vengateshm.kotlin_practice.coroutines

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

data class Badge(
  val newsCount: Int,
  val userName: String,
)

suspend fun requestNews(): Int {
  delay(500)
  println("Fetching news...")
  return 5
}

suspend fun requestUser(): String {
  delay(300)
  println("Fetching user data...")
//  return "Dummy User"
  throw Exception("Error fetching user data")
}

suspend fun loadBadge(): Badge = coroutineScope {
  val news = async { requestNews() }
  val user = async { requestUser() }
  Badge(news.await(), user.await())
}

fun main() = runBlocking {
  launch {
    try {
      println("Launching coroutine to load badge...")
      val badge = loadBadge()
      println("Badge loaded: News Count = ${badge.newsCount}, User Name = ${badge.userName}")
    } catch (e: Throwable) {
      println("Caught exception: ${e.message}")
    }
  }

  delay(1000)
  println("Main function finished.")
}