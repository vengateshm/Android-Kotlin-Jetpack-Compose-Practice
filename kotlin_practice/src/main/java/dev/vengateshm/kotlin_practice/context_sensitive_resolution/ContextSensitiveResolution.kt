package dev.vengateshm.kotlin_practice.context_sensitive_resolution

import dev.vengateshm.kotlin_practice.context_sensitive_resolution.Color.Companion.BLACK
import dev.vengateshm.kotlin_practice.context_sensitive_resolution.Color.Companion.KODEE
import dev.vengateshm.kotlin_practice.context_sensitive_resolution.Color.Companion.WHITE
import java.math.BigInteger

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

sealed interface NetworkError1 {
  data class Connection(val responseCode: Int) : NetworkError1
  data class Authentication(val message: String) : NetworkError1
  data object Permission : NetworkError1
  data object Unknown : NetworkError1
}

class Color(r: Int, g: Int, b: Int) {
  companion object {
    val WHITE: Color = Color(255, 255, 255)
    val BLACK: Color = Color(0, 0, 0)
    val KODEE: Color = Color(0, 0, 0)
    fun background(): Color = Color(0, 0, 0)
  }
}

//val Color.Companion.KODEE get() = Color(127, 82, 255)

fun testColor(color: Color) {
  when (color) {
    WHITE -> println("White")
    BLACK -> println("White")
    KODEE -> println("White")
    else -> println("Other")
  }
}

fun withJavaStatistics(bigInteger: BigInteger) {
  when (bigInteger) {
    BigInteger.ZERO, BigInteger.ONE, BigInteger.TWO, BigInteger.TEN -> println("Known big integer")
    else -> println("Unknown big integer")
  }
}