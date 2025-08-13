package dev.vengateshm.kotlin_practice.context_sensitive_resolution

enum class NetworkError {
  Connection,
  Authentication,
  Permission,
  Unknown
}

fun message(error: NetworkError): String = when (error) {
  NetworkError.Connection -> "Connection failed"
  NetworkError.Authentication -> "User is not logged in"
  NetworkError.Permission -> "You don't have permission for this action"
  NetworkError.Unknown -> "Unknown problem"
}

fun parseError(errorCode: Int): NetworkError = when (errorCode) {
  -1 -> NetworkError.Connection
  2 -> NetworkError.Authentication
  3 -> NetworkError.Permission
  else -> NetworkError.Unknown
}

fun log(error: NetworkError) {
  if (error == NetworkError.Unknown) {
    return
  }
  println("Error : ${error.name}")
}

fun main() {
  println(message(error = NetworkError.Connection))
  println(parseError(errorCode = 2))
  log(error = NetworkError.Authentication)
}